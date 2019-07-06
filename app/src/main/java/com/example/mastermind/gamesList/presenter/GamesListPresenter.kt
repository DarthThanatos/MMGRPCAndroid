package com.example.mastermind.gamesList.presenter

import com.example.mastermind.gamesList.view.ExisitingGamesView
import com.example.mastermind.protocol.Protocol
import server.GamesByName
import server.GreeterGrpc

interface GamesListPresenter{
    fun attachView(view: ExisitingGamesView)
    fun detachView()
    fun getGamesByName()
}

class GamesListPresenterImpl(private val host:String, private val port: Int = 50051, private val gameName: String): GamesListPresenter{

    private val protocol = Protocol(host, port)
    private var view: ExisitingGamesView? = null

    override fun getGamesByName() {
        view?.turnOnProgress()
        protocol.runInBackground(
            task = this::fetchGames,
            onResult = this::onGamesFetched
        )
    }

    private fun fetchGames(blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub) =
        protocol.getGamesByName(gameName, blockingStub)


    private fun onGamesFetched(gamesByName: GamesByName){
        view?.turnOffProgress()
        view?.displayGames(gamesByName)
    }

    override fun attachView(view: ExisitingGamesView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}