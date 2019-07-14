package com.example.mastermind.game.view

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.BLACK
import android.util.AttributeSet
import android.view.View
import com.example.mastermind.R
import com.example.mastermind.game.presenter.GamePresenter
import server.Color


class GuesserBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet), GameBoardProvider{

    override fun refresh() {
        invalidate()
    }

    private val paint = Paint()
    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config = GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)
    override fun config(): GameDisplayConfig = config

    override fun resources(): Resources = resources
    override fun touchOffset(): Int = config.widthForVerification.toInt()
    override fun presenter(): GamePresenter? = (context as GameView).presenter()


    private val listener = OnBoardTouchListener(this)

    fun activate(){
        setOnTouchListener(listener)
    }

    private fun drawDividingLines(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.BLACK
                val horizontalDividerY: Float = (MARGIN_FROM_BORDER + MARGIN_TO_VERIFIER_ROW / 2 + rowHeight).toFloat()
                drawLine(0.0f, horizontalDividerY, screenWidth.toFloat(), horizontalDividerY, paint)
                val verticalDividerX = screenWidth - widthForColors
                drawLine(verticalDividerX, 0.0f, verticalDividerX, screenHeight.toFloat(), paint)
            }
        }
    }

    private fun getTextDimension(dimensionSpec: (Rect) -> Int): Int{
        val bounds = Rect()
        paint.getTextBounds("?", 0, 1, bounds)
        return dimensionSpec(bounds)
    }

    private fun getQuestionMarkWidth(): Int{
        return getTextDimension { it.width() }
    }

    private fun getQuestionMarkHeight(): Int{
        return getTextDimension { it.height() }
    }

    private fun drawGuessingArea(canvas: Canvas?){
        canvas?.apply {
            config.apply{
                for (i in 0 .. 15){
                    for (j in 0 until 4){
                        paint.color = android.graphics.Color.GRAY
                        val additionalSeparator = if(i >= 1) MARGIN_TO_VERIFIER_ROW - MARGIN_BETWEEN_ROWS else 0
                        val cx: Float = widthForVerification + spaceBetweenColors * (j + 1) + j * rowHeight + choiceRadius
                        val cy: Float = MARGIN_FROM_BORDER + additionalSeparator + (MARGIN_BETWEEN_ROWS + rowHeight) * i + choiceRadius
                        drawCircle(cx, cy, choiceRadius, paint)
                        if(i == 0){
                            paint.color = BLACK
                            paint.textSize = resources.getDimensionPixelSize(R.dimen.questionMarkFontSize).toFloat()
                            drawText("?", 0, 1, cx - getQuestionMarkWidth() / 2, cy + getQuestionMarkHeight() / 2, paint)
                        }
                    }
                }
            }
        }
    }

    private fun drawVerificationArea(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.BLACK
                for (i in 1 until 16){
                    for (m in 0 until 2){
                        for (n in 0 until 2) {
                            val cx = (horizontalBorderMargin
                                    + n * (horizontalMarkersGap + 2 * verificationMarkerRadius) + verificationMarkerRadius)
                            val cy = (MARGIN_FROM_BORDER + MARGIN_TO_VERIFIER_ROW - MARGIN_BETWEEN_ROWS + (MARGIN_BETWEEN_ROWS + rowHeight) * i + verticalBorderMargin
                                    + m * (2 * verificationMarkerRadius + verticalMarkersGap) + verificationMarkerRadius)
                            drawCircle(cx, cy, verificationMarkerRadius, paint)
                        }
                    }
                }
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
//        touchResponder?.displayCurrentRow(canvas, paint)
        drawGuessingArea(canvas)
        drawVerificationArea(canvas)
        drawDividingLines(canvas)
//        touchResponder?.displayColorSelection(canvas)
    }


}