package com.example.mastermind.protocol

import io.grpc.stub.StreamObserver
import server.*
import java.util.*

interface GameRequester{
    fun createGame(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub): GameDescription
    fun getGamesByName(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub): GamesByName
    fun joinGame(userName: String, gameDescription: GameDescription, blockingStub: GreeterGrpc.GreeterBlockingStub): Player
    fun keepAlive(player: Player, asynchStub: GreeterGrpc.GreeterStub)
}

class GameRequesterImpl: GameRequester{
    override fun keepAlive(player: Player, asynchStub: GreeterGrpc.GreeterStub) {
        var shouldFinish = false
        val keepAliveObserver = asynchStub.keepAlive(object :StreamObserver<Player>{
            override fun onNext(value: Player) {
                println("Player: ${value.playerName} has exited the room")
                shouldFinish = true
            }

            override fun onError(t: Throwable) {
                t.printStackTrace()
            }

            override fun onCompleted() {
                println("${player.playerName} on completed")
            }

        })

        val timer = Timer()
        timer.schedule(object: TimerTask(){
            override fun run() {
                if(shouldFinish){
                    keepAliveObserver.onCompleted()
                    this.cancel()
                }
                else{
                    keepAliveObserver.onNext(player)
                }
            }
        }, 0, 1000)
    }

    private fun getGamesRequest(gameName: String) =
        GameDescription.newBuilder()
            .setGameName(gameName)
            .build()

    override fun getGamesByName(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub): GamesByName =
        blockingStub.getGamesByName(getGamesRequest(gameName))

    private fun newGameRequest(gameName: String) =
        GameDescription.newBuilder()
            .setGameName(gameName)
            .build()

    override fun createGame(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub) =
        blockingStub.createGame(newGameRequest(gameName))

    private fun newJoinRequest(userName: String, gameDescription: GameDescription) =
        Player.newBuilder()
            .setPlayerName(userName)
            .setGameId(gameDescription.gameId)
            .build()

    override fun joinGame(userName: String, gameDescription: GameDescription, blockingStub: GreeterGrpc.GreeterBlockingStub) =
        blockingStub.joinGame(newJoinRequest(userName, gameDescription))

}