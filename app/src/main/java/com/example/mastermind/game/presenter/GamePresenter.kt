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
}

class GamePresenterImpl(private val host: String, private val gameName: String, private val gameId: String, private val playerName: String): GamePresenter{

    private var view: GameView? = null
    private var protocol: Protocol? = null
    private var timerTask: TimerTask? = null

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
        view?.showWaitingProgress()
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
            view?.apply {
                displayJoinedPlayer(player)
                if(player.role == Role.VERIFIER){
                    val colorArr = getCombinationColorArr()
                    waitForGuesser(player, colorArr)
                }
                else{
                    waitForVerifier(player)
                }
            }
            keepAlive(player)
    }

    private fun waitForVerifier(player: Player){
        protocol?.runInBackground(
            task = waitForVerifierTask(player),
            onResult = onVerifierArrived()
        )

    }

    private fun waitForGuesser(player: Player, combinationArr: Array<Color>){
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
        Unit.also {  }
    }

    private fun onGuesserArrived() = {
        opponent: Player ->
            view?.apply {
                informOpponentJoinedGame(opponent)
                hideWaitingProgress()
                displayVerifierBoard()
            }?: throw IllegalStateException("View not initialized. Did you forgot to call attachView?")
        Unit.apply {  }
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
}