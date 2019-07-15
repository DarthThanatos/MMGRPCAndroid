package com.example.mastermind.game.view.guesser_board

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.GRAY
import android.util.AttributeSet
import android.view.View
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.view.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class GuesserBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet),
    GameBoardProvider {

    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config =
        GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)
    private val artist = GuesserBoardArtist(this)

    override fun config(): GameDisplayConfig = config
    override fun resources(): Resources = resources
    override fun touchOffset(): Int = config.widthForVerification.toInt()
    override fun presenter(): GamePresenter? = (context as GameView).presenter()

    private var verificationsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var guessedColorsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var currentlyGuessedCombination by Delegates.observable(intArrayOf(GRAY, GRAY, GRAY, GRAY), this::onFieldChanged)

    @Suppress("UNUSED_PARAMETER")
    private fun <T> onFieldChanged(prop: KProperty<*>, old: T, new: T){
        invalidate()
    }

    private val listener = OnBoardTouchListener(this)
    private val phases = GuesserBoardPhases(this)

    fun activate(){
        setOnTouchListener(listener)
        listener.touchableAreas = phases.guessingCombinationSpecificationPhase(0)
    }

    fun showAcceptSecretCombinationView() {
        listener.touchableAreas = phases.guessingCombinationWithAcceptView(listener.touchableAreas, guessedColorsSoFar.size)
    }

    fun hideAcceptSecretCombinationView(){
        listener.touchableAreas = phases.secretSpecificationWithoutAcceptView(listener.touchableAreas)
    }

    fun waitForGuesserTurn(){
        listener.touchableAreas = phases.waitPhase()
    }

    fun updateCurrentGuessedCombination(currentGuessedCombination: Array<Int>){
        this.currentlyGuessedCombination = currentGuessedCombination.copyOf().toIntArray()
    }

    override fun refresh() {
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        artist.apply {
            displayCurrentRow(canvas, guessedColorsSoFar.size)
            drawGuessingArea(canvas, currentlyGuessedCombination, guessedColorsSoFar)
            drawVerificationArea(canvas)
            drawDividingLines(canvas)
        }
        for(touchableArea in listener.touchableAreas){
            touchableArea.display(canvas)
        }
    }


}