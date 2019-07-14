package com.example.mastermind.game.view

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color.GRAY
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.mastermind.game.presenter.GamePresenter
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class VerifierBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet),
    GameBoardProvider {

    override fun refresh() {
        invalidate()
    }

    private val paint = Paint()
    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config = GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)
    override fun config(): GameDisplayConfig = config

    override fun presenter(): GamePresenter? = (context as GameView).presenter()
    override fun resources(): Resources = resources
    override fun touchOffset(): Int = 0

    private var verificationsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var guessedColorsSoFar by Delegates.observable(listOf<Array<Int>>(), this::onFieldChanged)
    private var secretCombinationColors by Delegates.observable(intArrayOf(GRAY, GRAY, GRAY, GRAY), this::onFieldChanged)

    private val listener = OnBoardTouchListener(this)

    fun activate(){
        setOnTouchListener(listener)
        val secretSelectionTouchableAreas = secretSelectionTouchableAreas()
        listener.touchableAreas = secretSelectionTouchableAreas
    }

    private fun secretSelectionTouchableAreas(): MutableList<OnBoardTouchListener.TouchableArea>{
        val secretSelectionTouchableAreas = mutableListOf<OnBoardTouchListener.TouchableArea>()
        for (j in 0 until 4){
            val centerOfColorSelector = CenterOfColorSelector(this, 0, j, 0)
            val leftColorSelector = SecretCombinationLeftColorSelector(this, 0, j, 0, centerOfColorSelector)
            val rightColorSelector = SecretCombinationRightColorSelector(this, 0, j, 0, centerOfColorSelector)
            val compundSelector = CombinationSelector(leftColorSelector, rightColorSelector, centerOfColorSelector)
            secretSelectionTouchableAreas.add(compundSelector)
        }
        return secretSelectionTouchableAreas
    }

    fun showAcceptSecretCombinationView(){
        val secretSelectionTouchableAreas = listener.touchableAreas.filter { it !is AcceptSecretCombinationView }.toMutableList()
        secretSelectionTouchableAreas.add(AcceptSecretCombinationView(this))
        listener.touchableAreas = secretSelectionTouchableAreas
    }

    fun hideAcceptSecretCombinationView(){
        val secretSelectionTouchableAreas = listener.touchableAreas.filter { it !is AcceptSecretCombinationView }.toMutableList()
        listener.touchableAreas = secretSelectionTouchableAreas
    }

    @Suppress("UNUSED_PARAMETER")
    private fun <T> onFieldChanged(prop: KProperty<*>, old: T, new: T){
        invalidate()
    }


    private fun drawDividingLines(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.BLACK
                val horizontalDividerY: Float = (MARGIN_FROM_BORDER + MARGIN_TO_VERIFIER_ROW / 2 - MARGIN_BETWEEN_ROWS + (MARGIN_BETWEEN_ROWS + rowHeight) * (ROWS_AMOUNT - 1)).toFloat()
                drawLine(0.0f, horizontalDividerY, screenWidth.toFloat(), horizontalDividerY, paint)
                drawLine(widthForColors, 0.0f, widthForColors, screenHeight.toFloat(), paint)
            }
        }
    }

    private fun drawGuessingArea(canvas: Canvas?){
        canvas?.apply {
            config.apply{
                paint.color = android.graphics.Color.GRAY
                for (i in 0 .. 15){
                    for (j in 0 until 4){
                        val additionalSeparator = if(i == 15) MARGIN_TO_VERIFIER_ROW - MARGIN_BETWEEN_ROWS else 0
                        val cx: Float = spaceBetweenColors * (j + 1) + j * rowHeight + choiceRadius
                        val cy: Float = MARGIN_FROM_BORDER + additionalSeparator + (MARGIN_BETWEEN_ROWS + rowHeight) * i + choiceRadius
                        if(i == 15){
                            paint.color = secretCombinationColors[j]
                        }
                        drawCircle(cx, cy, choiceRadius, paint)
                    }
                }
            }
        }
    }

    private fun drawVerificationArea(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.BLACK
                for (i in 0 until 15){
                    for (m in 0 until 2){
                        for (n in 0 until 2) {
                            val cx = (widthForColors + horizontalBorderMargin
                                    + n * (horizontalMarkersGap + 2 * verificationMarkerRadius) + verificationMarkerRadius)
                            val cy = (MARGIN_FROM_BORDER + (MARGIN_BETWEEN_ROWS + rowHeight) * i + verticalBorderMargin
                                    + m * (2 * verificationMarkerRadius + verticalMarkersGap) + verificationMarkerRadius)
                            drawCircle(cx, cy, verificationMarkerRadius, paint)
                        }
                    }
                }
            }
        }
    }

    fun updateSecretElements(elements: Array<Int>) {
        secretCombinationColors = elements.copyOf().toIntArray()
    }

    override fun onDraw(canvas: Canvas?) {
        drawGuessingArea(canvas)
        drawVerificationArea(canvas)
        drawDividingLines(canvas)
        for(touchableArea in listener.touchableAreas){
            touchableArea.display(canvas)
        }
    }
}