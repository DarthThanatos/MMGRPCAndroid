package com.example.mastermind.protocol

import server.Color
import server.Combination
import server.Player

class Util{

    companion object{

        @JvmStatic
        fun newCombinationRequest(
            combinationArray: Array<Color>,
            player: Player
        ) =
            Combination.newBuilder()
                .setFirst(combinationArray[0])
                .setSecond(combinationArray[1])
                .setThird(combinationArray[2])
                .setFourth(combinationArray[3])
                .setPlayer(player)
                .build()
    }

}