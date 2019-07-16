package com.example.mastermind.game.presenter

import android.util.Log
import com.example.mastermind.R
import com.example.mastermind.game.view.GameView
import com.example.mastermind.game.view.verification_dialog.VerificationDialogView
import com.example.mastermind.protocol.Protocol
import server.*
import java.lang.IllegalStateException
import java.util.*

interface GamePresenter{
    fun attachView(view: GameView)
    fun detachView()
    fun joinGame()
    fun onNextSecretColor(index: Int)
    fun onPrevSecretColor(index: Int)
    fun onNextGuessedColor(index: Int)
    fun onPrevGuessedColor(index: Int)
    fun onNextVerificationColor(index: Int, verificationDialogView: VerificationDialogView)
    fun onPrevVerificationColor(index: Int, verificationDialogView: VerificationDialogView)
    fun onSecretAccepted()
    fun onGuessAccepted()
    fun onVerificationAccepted()
}

class GamePresenterImpl(private val host: String, private val gameName: String, private val gameId: String, private val playerName: String): GamePresenter{

    private var view: GameView? = null
    private var protocol: Protocol? = null
    private var timerTask: TimerTask? = null
    private val selectionLogic = SelectionLogic()
    private lateinit var player: Player

    override fun attachView(view: GameView) {
        this.view = view
        protocol = Protocol(host = host, port = 50051)
    }

    override fun detachView() {
        view = null
        timerTask?.cancel()
        timerTask = null
        protocol?.shutdown()
        protocol = null
    }

    override fun joinGame() {
        Log.d(GamePresenter::class.java.simpleName, "Joining game named: $gameName")
        protocol?.runInBackground(
            task = joinGameTask(),
            onResult = onJoinedGame()
        )
    }

    private fun joinGameTask() = {
        blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
            protocol?.joinGame(userName = playerName, gameId = UUID.fromString(gameId), blockingStub = blockingStub)
                ?: throw IllegalStateException("Protocol not initialized. Did you forgot to call attachView?")
    }

    private fun onJoinedGame() = {
        player: Player ->
            this.player = player
            view?.apply {
                displayJoinedPlayer(player)
                if(player.role == Role.VERIFIER){
                    displayVerifierBoard()
                }
                else{
                    waitForVerifier(player)
                }
            }
            Unit.apply {  }
    }

    private fun waitForVerifier(player: Player){
        view?.showWaitingProgress()
        protocol?.runInBackground(
            task = waitForVerifierTask(player),
            onResult = onVerifierArrived()
        )

    }

    private fun waitForGuesser(player: Player, combinationArr: Array<Color>){
        view?.showWaitingProgress()
        protocol?.runInBackground(
            task = waitForGuesserTask(player, combinationArr),
            onResult = onGuesserArrived()
        )
    }

    private fun waitForGuesserTask(player: Player, combinationArr: Array<Color>) = {
            blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
                protocol?.waitForGuesser(combinationArr, player, blockingStub)
                    ?: throw IllegalStateException("Protocol not initialized. Did you forgot to call attachView?")
        }

    private fun waitForVerifierTask(player: Player)= {
            blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
                protocol?.waitForVerifier(player, blockingStub)
                    ?: throw IllegalStateException("Protocol not initialized. Did you forgot to call attachView?")

    }

    private fun onVerifierArrived() = {
        opponent: Player ->
            view?.apply {
                informOpponentJoinedGame(opponent)
                hideWaitingProgress()
                displayGuesserBoard()
            } ?: throw IllegalStateException("View not initialized. Did you forgot to call attachView?")
            keepAlive(player)
    }

    private fun onGuesserArrived() = {
        opponent: Player ->
            view?.apply {
                informOpponentJoinedGame(opponent)
                hideWaitingProgress()
            }?: throw IllegalStateException("View not initialized. Did you forgot to call attachView?")
            keepAlive(player)
            subscribeVerifier()
    }

    private fun subscribeVerifier(){
        protocol?.runInBackground(
            task = subscribeVerifierTask()
        )
    }

    private fun subscribeVerifierTask() = {
        blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
            protocol?.subsribeForGuesses(player, asynchStub, this::onVerifyPhase, this::onVerifierEndGame)
    }

    private fun keepAlive(player: Player){
        protocol?.runInBackground(
            task = keepAliveTask(player),
            onResult = {timerTask = it}
        )
    }

    private fun keepAliveTask(player: Player) = {
        blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
            protocol?.keepAlive(player, asynchStub, this::onOpponentLeftGame)
    }

    private fun onOpponentLeftGame(opponent: Player) {
        view?.informOpponentLeftGame(opponent)
    }


    private fun onSecretColorChanged(index: Int, selectColor: (Int) -> Boolean){
        val allSelected = selectColor(index)
        view?.apply{
            displaySecretCombination(selectionLogic.secretEnumsToPresentation())
            if(allSelected){
                displayAcceptSecretCombination()
            }
            else{
                hideAcceptSecretCombination()
            }
        }
    }

    override fun onNextSecretColor(index: Int){
        onSecretColorChanged(index, selectionLogic::onNextSecretColor)
    }

    override fun onPrevSecretColor(index: Int){
        onSecretColorChanged(index, selectionLogic::onPrevSecretColor)
    }

    private fun onCurrentGuessesChanged(index: Int, selectColor: (Int) -> Boolean) {
        val allSelected = selectColor(index)
        view?.apply{
            displayCurrentGuessedColors(selectionLogic.currentGuessedEnumsToPresentation())
            if(allSelected){
                displayAcceptGuessedCombination()
            }
            else{
                hideAcceptCurrentGuessedColors()
            }
        }
    }

    override fun onNextGuessedColor(index: Int) {
        onCurrentGuessesChanged(index, selectionLogic::onNextGuessedColor)
    }

    override fun onPrevGuessedColor(index: Int) {
        onCurrentGuessesChanged(index, selectionLogic::onPrevGuessedColor)
    }

    private fun onVerificationColorChanged(index: Int, verificationDialogView: VerificationDialogView, selectColor: (Int)->Boolean){
       val allSelected = selectColor(index)
        verificationDialogView.apply {
            updateVerificationMarkers(selectionLogic.currentVerificationMarkersEnumsToPresentation())
            if(allSelected){
                showAcceptButton()
            }
            else{
                hideAcceptButton()
            }
        }
    }

    override fun onNextVerificationColor(index: Int, verificationDialogView: VerificationDialogView) {
        onVerificationColorChanged(index, verificationDialogView, selectionLogic::onNextVerificationMarker)
    }

    override fun onPrevVerificationColor(index: Int, verificationDialogView: VerificationDialogView) {
        onVerificationColorChanged(index, verificationDialogView, selectionLogic::onPrevVerificationMarker)
    }

    override fun onSecretAccepted() {
        view?.apply {
            waitForVerifierTurn(
                selectionLogic.guessesSoFarToPresentation(),
                selectionLogic.verificationsSoFarToPresentation()
            )
            showWaitingProgress()
        }
        val colorArr = selectionLogic.getSecretArrayOfColors()
        waitForGuesser(player, colorArr)
    }

    override fun onGuessAccepted() {
        selectionLogic.rememberCurrentGuessedColorSequence()
        view?.apply {
            showWaitingProgress()
            waitForGuesserTurn()
        }
        protocol?.runInBackground(
            task = sendGuessesTask(),
            onResult = onVerificationArrived()
        )
    }

    private fun onVerifyPhase(combination: Combination){
        selectionLogic.rememberColorSequence(combination)
        selectionLogic.clearCurrentVerificationMarkers()
        view?.apply {
            hideWaitingProgress()
            promptVerification(
                selectionLogic.combinationEnumsToPresentation(combination)
            )
        }

    }

    override fun onVerificationAccepted() {
        selectionLogic.rememberCurrentVerificationSequence()
        sendVerification()
        view?.apply{
            showWaitingProgress()
            waitForVerifierTurn(
                selectionLogic.guessesSoFarToPresentation(),
                selectionLogic.verificationsSoFarToPresentation()
            )
        }
    }

    private fun sendVerification(){
        protocol?.runInBackground(
            task = sendVerificationTask()
        )
    }

    private fun sendVerificationTask() = {
        blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
            protocol?.verify(player, selectionLogic.getCurrentVerificationMarkesArrayOfColors(), blockingStub)
    }

    private fun sendGuessesTask() = {
        blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub ->
            protocol?.guess(selectionLogic.getCurrentGuessedArrayOfColors(), player, blockingStub)
                ?: throw IllegalStateException("Protocol not initialized. Did you forgot to call attachView?")
    }

    private fun onVerificationArrived() = {
        verification: Verification ->
            selectionLogic.rememberVerification(verification)
            selectionLogic.clearCurrentGuesses()
            view?.apply {
                hideWaitingProgress()
                if(verification.endGame){
                    onGuesserEndGame()
                }
                else {
                    onGuesserTurn(
                        selectionLogic.guessesSoFarToPresentation(),
                        selectionLogic.verificationsSoFarToPresentation()
                    )
                }
            }
            Unit.apply {  }
    }

    private fun onGuesserEndGame(){
        if (selectionLogic.guesserGuessed()){
            val msg = "You guessed the secret combination and so kicked the verifier's ass!"
            val title = "You won!"
            view?.informAboutGameResult(title, msg, R.raw.applause)
        }
        else{
            val msg = "You failed to guess the secret combination and thus your ass has been kicked, you loser!"
            val title = "You're a complete failure!"
            view?.informAboutGameResult(title, msg, R.raw.fail)

        }
    }

    private fun onVerifierEndGame(){
        if (selectionLogic.guesserGuessed()){
            val msg = "The gusser guessed the secret combination and so kicked your ass!"
            val title = "You're a complete failure!"
            view?.informAboutGameResult(title, msg, R.raw.fail)
        }
        else{
            val msg = "The guesser failed to guess the secret combination and thus their ass has been kicked, congrats!"
            val title = "You won"
            view?.informAboutGameResult(title, msg, R.raw.applause)

        }
    }
}