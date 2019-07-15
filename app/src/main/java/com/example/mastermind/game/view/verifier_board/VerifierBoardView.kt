package com.example.mastermind.game.view.verifier_board

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color.GRAY
import android.util.AttributeSet
import android.view.View
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.view.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class VerifierBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet),
    GameBoardProvider {

    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config =
        GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)
    private val artist = VerifierBoardArtist(this)

    override fun config(): GameDisplayConfig = config
    override fun presenter(): GamePresenter? = (context as GameView).presenter()
    override fun resources(): Resources = resources
    override fun touchOffset(): Int = 0

    private var verificationsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var guessedColorsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var secretCombinationColors by Delegates.observable(intArrayOf(GRAY, GRAY, GRAY, GRAY), this::onFieldChanged)

    @Suppress("UNUSED_PARAMETER")
    private fun <T> onFieldChanged(prop: KProperty<*>, old: T, new: T){
        invalidate()
    }

    private val listener = OnBoardTouchListener(this)
    private val phases = VerifierBoardPhases(this)

    fun activate(){
        setOnTouchListener(listener)
        listener.touchableAreas = phases.secretSpecificationInit()
    }

    fun showAcceptSecretCombinationView(){
        listener.touchableAreas = phases.secretSpecificationWithAcceptView(listener.touchableAreas)
    }

    fun hideAcceptSecretCombinationView(){
        listener.touchableAreas = phases.secretSpecificationWithoutAcceptView(listener.touchableAreas)
    }

    fun waitForVerifierTurn(){
        listener.touchableAreas = phases.waitPhase()
    }

    fun updateSecretElements(elements: Array<Int>) {
        secretCombinationColors = elements.copyOf().toIntArray()
    }

    override fun refresh() {
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        artist.apply {
            drawGuessingArea(canvas, secretCombinationColors, guessedColorsSoFar)
            drawVerificationArea(canvas, verificationsSoFar)
            drawDividingLines(canvas)
        }
        for(touchableArea in listener.touchableAreas){
            touchableArea.display(canvas)
        }
    }
}