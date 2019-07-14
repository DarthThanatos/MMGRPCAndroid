package com.example.mastermind.game.presenter

import android.util.Log
import com.example.mastermind.game.view.GameView
import com.example.mastermind.protocol.Protocol
import server.Color
import server.GreeterGrpc
import server.Player
import server.Role
import java.lang.IllegalStateException
import java.util.*

interface GamePresenter{
    fun attachView(view: GameView)
    fun detachView()
    fun joinGame()
    fun onNextSecretColor(index: Int)
    fun onPrevSecretColor(index: Int)
    fun onSecretAccepted()
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
        Log.d(GamePresenter::class.java.simpleName, "Verifier arrived")
        keepAlive(player)
    }

    private fun onGuesserArrived() = {
        opponent: Player ->
            view?.apply {
                informOpponentJoinedGame(opponent)
                hideWaitingProgress()
            }?: throw IllegalStateException("View not initialized. Did you forgot to call attachView?")
        keepAlive(player)
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

    override fun onSecretAccepted() {
        val colorArr = selectionLogic.getSecretArrayOfColors()
        waitForGuesser(player, colorArr)
    }
}