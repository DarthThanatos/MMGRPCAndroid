package com.example.mastermind.game.presenter

import android.graphics.Color.*
import server.Color
import server.Combination
import server.Verification
import server.VerificationMarker


class SelectionLogic {

    private var currentColor = -1 //for selecting guess OR secret combination element
    private val currentGuessedColors = intArrayOf(-1, -1, -1, -1)
    private val secretCombination = intArrayOf(-1, -1, -1, -1) // changed by verifier only
    private val colorSequencesSoFar = mutableListOf<Array<Color>>()

    private var currentVerificationMarker = -1
    private val currentVerificationMarkers = intArrayOf(-1, -1, -1, -1)
    private val verificationsSoFar = mutableListOf<Array<VerificationMarker>>()

    private val colorOrder = listOf(-1, Color.RED.ordinal, Color.BLUE.ordinal, Color.GREEN.ordinal, Color.YELLOW.ordinal, Color.PURPLE.ordinal, Color.ORANGE.ordinal)
    private val enumToColor = mapOf(
        -1 to GRAY,
        Color.RED.ordinal to RED,
        Color.BLUE.ordinal to BLUE,
        Color.GREEN.ordinal to GREEN,
        Color.YELLOW.ordinal to YELLOW,
        Color.PURPLE.ordinal to android.graphics.Color.rgb(128,0,128),
        Color.ORANGE.ordinal to android.graphics.Color.rgb(255,140,0)
    )
    private val verificationOrder = listOf(-1, VerificationMarker.NONE.ordinal, VerificationMarker.GOOD_COLOR.ordinal, VerificationMarker.GOOD_PLACE_AND_COLOR.ordinal)
    private val enumToVerificationColor = mapOf(
        -1 to GRAY,
        VerificationMarker.NONE.ordinal to BLACK,
        VerificationMarker.GOOD_COLOR.ordinal to RED,
        VerificationMarker.GOOD_PLACE_AND_COLOR.ordinal to WHITE
    )

    private fun onColorChanged(index: Int, color: Int, collection: IntArray, colorModificationFun: (Int)->Unit): Boolean{
        collection[index] = color
        colorModificationFun(color)
        return collection.all { it != -1 }
    }

    private fun onNextColor(index: Int, order: List<Int>, collection: IntArray, presentColor: Int, colorModificationFun: (Int)->Unit): Boolean{
        val colorPosition = order.indexOf(presentColor)
        val nextColorPosition = if(colorPosition == order.lastIndex) 0 else colorPosition + 1
        return onColorChanged(index, order[nextColorPosition], collection, colorModificationFun)
    }

    private fun onPrevColor(index: Int, order: List<Int>, collection: IntArray, presentColor: Int, colorModificationFun: (Int)->Unit): Boolean{
        val colorPosition = order.indexOf(presentColor)
        val prevColorPosition = if(colorPosition == 0) order.lastIndex else colorPosition - 1
        return onColorChanged(index, order[prevColorPosition], collection, colorModificationFun)
    }

    fun onNextSecretColor(index: Int)
        = onNextColor(index, colorOrder, secretCombination, currentColor) {currentColor = it}

    fun onPrevSecretColor(index: Int)
        = onPrevColor(index, colorOrder, secretCombination, currentColor) {currentColor = it}

    fun onNextGuessedColor(index: Int)
        = onNextColor(index, colorOrder, currentGuessedColors, currentColor) {currentColor = it}

    fun onPrevGuessedColor(index: Int)
        = onPrevColor(index, colorOrder, currentGuessedColors, currentColor)  {currentColor = it}

    fun onNextVerificationMarker(index: Int)
        = onNextColor(index, verificationOrder, currentVerificationMarkers, currentVerificationMarker)  {currentVerificationMarker = it}

    fun onPrevVerificationMarker(index: Int)
        = onPrevColor(index, verificationOrder, currentVerificationMarkers, currentVerificationMarker)  {currentVerificationMarker = it}

    fun getSecretArrayOfColors() =
        secretCombination.map { Color.forNumber(it) }.toTypedArray()

    fun getCurrentGuessedArrayOfColors() =
        currentGuessedColors.map { Color.forNumber(it) }.toTypedArray()

    fun getCurrentVerificationMarkesArrayOfColors() =
        currentVerificationMarkers.map { VerificationMarker.forNumber(it) }.toTypedArray()

    fun secretEnumsToPresentation() =
        secretCombination.map { enumToColor.getValue(it) }.toTypedArray()

    fun currentGuessedEnumsToPresentation() =
        currentGuessedColors.map { enumToColor.getValue(it) }.toTypedArray()

    fun combinationEnumsToPresentation(combination: Combination) =
        combinationToColorArr(combination).map {enumToColor.getValue(it.ordinal)}.toTypedArray()

    fun currentVerificationMarkersEnumsToPresentation() =
        currentVerificationMarkers.map { enumToVerificationColor.getValue(it) }.toTypedArray()

    fun verificationsSoFarToPresentation() =
        verificationsSoFar.map { it.map { enum -> enumToVerificationColor.getValue(enum.ordinal) }.toTypedArray() }

    fun guessesSoFarToPresentation() =
        colorSequencesSoFar.map { it.map { enum -> enumToColor.getValue(enum.ordinal) }.toTypedArray() }

    private fun combinationToColorArr(combination: Combination)
        = arrayOf(combination.first, combination.second, combination.third, combination.fourth)

    fun rememberColorSequence(combination: Combination){
        val colors = combinationToColorArr(combination)
        colorSequencesSoFar.add(colors)
    }

    fun rememberCurrentGuessedColorSequence(){
        colorSequencesSoFar.add(currentGuessedColors.map {Color.forNumber(it)}.toTypedArray())
    }

    fun rememberCurrentVerificationSequence(){
        verificationsSoFar.add(currentVerificationMarkers.map {VerificationMarker.forNumber(it)}.toTypedArray())
    }

    private fun verificationToVerificationMarkersArray(verification: Verification)
        = arrayOf(verification.first, verification.second, verification.third, verification.fourth)

    fun rememberVerification(verification: Verification){
        val verificationMarkers = verificationToVerificationMarkersArray(verification)
        verificationsSoFar.add(verificationMarkers)
    }

    fun clearCurrentVerificationMarkers(){
        currentVerificationMarker = -1
        for (j in 0 until 4)
            currentVerificationMarkers[j] = -1
    }

    fun clearCurrentGuesses(){
        currentColor = -1
        for(j in 0 until 4)
            currentGuessedColors[j] = -1
    }

    fun guesserGuessed(): Boolean{
        val lastVerification = verificationsSoFar.last()
        return lastVerification.all { it == VerificationMarker.GOOD_PLACE_AND_COLOR }
    }
}