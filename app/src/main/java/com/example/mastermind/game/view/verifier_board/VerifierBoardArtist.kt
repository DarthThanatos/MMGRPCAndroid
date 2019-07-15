package com.example.mastermind.game.view.verifier_board

import android.graphics.Canvas
import android.graphics.Paint
import com.example.mastermind.game.view.MARGIN_BETWEEN_ROWS
import com.example.mastermind.game.view.MARGIN_FROM_BORDER
import com.example.mastermind.game.view.MARGIN_TO_VERIFIER_ROW
import com.example.mastermind.game.view.ROWS_AMOUNT

class VerifierBoardArtist(verifierBoardViewImpl: VerifierBoardViewImpl){

    private val config = verifierBoardViewImpl.config()
    private val paint = Paint()

    fun drawDividingLines(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.BLACK
                val horizontalDividerY: Float = (MARGIN_FROM_BORDER + MARGIN_TO_VERIFIER_ROW / 2 - MARGIN_BETWEEN_ROWS + (MARGIN_BETWEEN_ROWS + rowHeight) * (ROWS_AMOUNT - 1)).toFloat()
                drawLine(0.0f, horizontalDividerY, screenWidth.toFloat(), horizontalDividerY, paint)
                drawLine(widthForColors, 0.0f, widthForColors, screenHeight.toFloat(), paint)
            }
        }
    }

    fun drawGuessingArea(canvas: Canvas?, secretCombinationColors: IntArray, guessedColorsSoFar: List<Array<Int>>){
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

    fun drawVerificationArea(canvas: Canvas?, verificationsSoFar: List<Array<Int>>){
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

}