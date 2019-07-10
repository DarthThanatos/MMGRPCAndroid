package com.example.mastermind.protocol

import io.grpc.stub.StreamObserver
import server.*
import java.util.*

interface GameRequester{
    fun createGame(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub): GameDescription
    fun getGamesByName(gameName: String, blockingStub: GreeterGrpc.GreeterBlockingStub): GamesByName
    fun joinGame(userName: String, gameId: UUID, blockingStub: GreeterGrpc.GreeterBlockingStub): Player
    fun keepAlive(player: Player, asynchStub: GreeterGrpc.GreeterStub, onOpponentLeftRoom: OnOpponentLeftRoomTask): TimerTask
}

typealias OnOpponentLeftRoomTask = (Player) -> Unit

class GameRequesterImpl: GameRequester{


    override fun keepAlive(player: Player, asynchStub: GreeterGrpc.GreeterStub, onOpponentLeftRoom: OnOpponentLeftRoomTask): TimerTask {
        var shouldFinish = false
        val keepAliveObserver = asynchStub.keepAlive(object :StreamObserver<Player>{
            override fun onNext(value: Player) {
                onOpponentLeftRoom(value)
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
        val task = object: TimerTask() {
            override fun run() {
                if (shouldFinish) {
                    keepAliveObserver.onCompleted()
                    this.cancel()
                } else {
                    keepAliveObserver.onNext(player)
                }
            }
        }
        timer.schedule(task, 0, 1000)
        return task
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

    private fun newJoinRequest(userName: String, gameId: UUID) =
        Player.newBuilder()
            .setPlayerName(userName)
            .setGameId(gameId.toString())
            .build()

    override fun joinGame(userName: String, gameId: UUID, blockingStub: GreeterGrpc.GreeterBlockingStub) =
        blockingStub.joinGame(newJoinRequest(userName, gameId))

}