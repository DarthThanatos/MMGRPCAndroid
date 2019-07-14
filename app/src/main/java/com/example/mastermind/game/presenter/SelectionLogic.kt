package com.example.mastermind.game.presenter

import android.graphics.Color.*
import server.Color
import server.VerificationMarker


class SelectionLogic {

    private val secretCombination = intArrayOf(-1, -1, -1, -1) // changed by verifier only
    private var currentColor = -1 //for selecting guess OR secret combination element
    private val currentColors = intArrayOf(-1, -1, -1, -1)
    private val colorSequencesSoFar = mutableListOf<Array<Color>>()
    private var currentVerificationMarker = -1
    private val currentVerifications = intArrayOf(-1, -1, -1, -1)
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
    private val verificationOrder = listOf(-1, VerificationMarker.NONE, VerificationMarker.GOOD_COLOR, VerificationMarker.GOOD_PLACE_AND_COLOR)
    private val enumToVerificationColor = mapOf(
        -1 to BLACK,
        VerificationMarker.NONE.ordinal to GRAY,
        VerificationMarker.GOOD_COLOR.ordinal to RED,
        VerificationMarker.GOOD_PLACE_AND_COLOR.ordinal to WHITE
    )

    fun rememberColorSequence(colors: Array<Color>){

    }

    fun rememberVerificationSequence(markers: Array<VerificationMarker>){

    }

    fun onNextVerificationMarker(index: Int): Boolean{
        return false
    }

    private fun onColorChanged(index: Int, color: Int): Boolean{
        secretCombination[index] = color
        currentColor = color
        return secretCombination.all { it != -1 }
    }

    fun onNextSecretColor(index: Int): Boolean{
        val colorPosition = colorOrder.indexOf(currentColor)
        val nextColorPosition = if(colorPosition == colorOrder.lastIndex) 0 else colorPosition + 1
        return onColorChanged(index, colorOrder[nextColorPosition])
    }

    fun onPrevSecretColor(index: Int): Boolean{
        val colorPosition = colorOrder.indexOf(currentColor)
        val prevColorPosition = if(colorPosition == 0) colorOrder.lastIndex else colorPosition - 1
        return onColorChanged(index, colorOrder[prevColorPosition])
    }

    fun getSecretArrayOfColors() =
        secretCombination.map { Color.forNumber(it) }.toTypedArray()

    fun secretEnumsToPresentation() =
        secretCombination.map {enumToColor[it]!!}.toTypedArray()
}