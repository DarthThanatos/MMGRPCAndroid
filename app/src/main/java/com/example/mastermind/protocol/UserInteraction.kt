package com.example.mastermind.protocol

import server.Color
import server.GameDescription
import server.GamesByName
import server.VerificationMarker
import java.lang.NumberFormatException

interface UserInteraction{
    fun askUserName(): String
    fun askIfCreateGame(userName: String): Boolean
    fun askGameName(userName: String): String
    fun getGameIndex(userName: String, gamesByName: GamesByName): Int
    fun getSecretCombination(userName: String, logMsg: String): Array<Color>
    fun getVerificationCombination(userName: String, logMsg: String): Array<VerificationMarker>
}

class UserInteractionImpl: UserInteraction{

    private val verificationMap = mapOf("c" to VerificationMarker.GOOD_COLOR, "b" to VerificationMarker.GOOD_PLACE_AND_COLOR, "x" to VerificationMarker.NONE)

    private val colorMap = mapOf("r" to Color.RED, "b" to Color.BLUE, "g" to Color.GREEN, "y" to Color.YELLOW, "p" to Color.PURPLE, "o" to Color.ORANGE)

    private fun <String, V> inputOk(inputList: List<String>, map: Map<String, V>): Boolean {
        val correctSize = 4
        if (inputList.size != correctSize) return false
        if (inputList.any {!map.containsKey(it)}) return false
        return true
    }

    private inline fun <reified V> getCombination(userName: String, logMsg: String, map: Map<String, V>): Array<V>{
        var combinationStrings: List<String>
        do {
            println("$userName - $logMsg")
            println(map)
            combinationStrings = readLine()!!.split(" ")
        } while (!inputOk(combinationStrings, map))
        return combinationStrings.map { map[it]!! }.toTypedArray()
    }

    override fun getSecretCombination(userName: String, logMsg: String) =
        getCombination(userName, logMsg, colorMap)

    override fun getVerificationCombination(userName: String, logMsg: String): Array<VerificationMarker> =
        getCombination(userName, logMsg, verificationMap)

    private fun stringGameDescription(index: Int, gameDescription: GameDescription) =
        "$index ${gameDescription.gameId}, checker: ${gameDescription.verifier.playerName}, " +
                "guesser: ${gameDescription.guesser.playerName}, ${gameDescription.creationDate}"

    override fun getGameIndex(userName: String, gamesByName: GamesByName): Int {
        val availableGames = gamesByName.gamesList.foldIndexed(""){
                i, acc, gameDescription -> "$acc\n${stringGameDescription(i, gameDescription)}"
        }
        val prompt = "$userName - games for name ${gamesByName.name}:$availableGames\nPick one:"
        var index = -1
        do{
            println(prompt)
            val strNum = readLine()!!
            try{
                index = strNum.toInt()
            }catch (e: NumberFormatException){
                e.printStackTrace()
                continue
            }
        } while(index >= gamesByName.gamesList.size || index <= -1)
        return index
    }

    override fun askGameName(userName: String): String {
        println("$userName - Type game name:")
        return readLine()!!
    }

    override fun askUserName(): String {
        println("Type your name:")
        return readLine()!!
    }

    override fun askIfCreateGame(userName: String): Boolean{
        var answer: String
        do {
            println("$userName - Do you want to create new game? [y/n]")
            answer = readLine()!!
        } while (answer != "y" && answer != "n")
        return answer == "y"
    }

}
