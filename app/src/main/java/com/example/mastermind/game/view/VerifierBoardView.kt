package com.example.mastermind.game.view

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import server.Color

interface VerifierBoardView{
    fun getSecretCombinationColorArr(): Array<Color>
}

class VerifierBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet), VerifierBoardView{

    private val paint = Paint()
    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config = GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)

    override fun getSecretCombinationColorArr(): Array<Color> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun onDraw(canvas: Canvas?) {
        drawGuessingArea(canvas)
        drawVerificationArea(canvas)
        drawDividingLines(canvas)
    }

}