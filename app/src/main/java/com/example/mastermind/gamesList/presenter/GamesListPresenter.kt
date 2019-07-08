package com.example.mastermind.gamesList.presenter

import com.example.mastermind.gamesList.view.ExisitingGamesView
import com.example.mastermind.protocol.Protocol
import server.GamesByName
import server.GreeterGrpc
import java.lang.IllegalStateException

interface GamesListPresenter{
    fun attachView(view: ExisitingGamesView)
    fun detachView()
    fun getGamesByName()
}

class GamesListPresenterImpl(private val host:String, private val port: Int = 50051, private val gameName: String): GamesListPresenter{

    private var protocol: Protocol? = null
    private var view: ExisitingGamesView? = null

    override fun getGamesByName() {
        view?.turnOnProgress()
        protocol?.runInBackground(
            task = this::fetchGames,
            onResult = this::onGamesFetched
        )
    }

    private fun fetchGames(blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub) =
        protocol?.getGamesByName(gameName, blockingStub)
            ?: throw IllegalStateException("Protocol not initialized; did you forget to call attachView?")


    private fun onGamesFetched(gamesByName: GamesByName){
        view?.apply {
            turnOffProgress()
            displayGames(gamesByName)
        } ?: throw IllegalStateException("Presenter should have GamesListView attached")
    }


    override fun attachView(view: ExisitingGamesView) {
        protocol = Protocol(host, port)
        this.view = view
    }

    override fun detachView() {
        view = null
        protocol?.shutdown()
        protocol = null
    }
}