package com.example.mastermind.game.view.guesser_board

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.CYAN
import android.graphics.Paint
import android.graphics.Rect
import com.example.mastermind.R
import com.example.mastermind.game.view.MARGIN_BETWEEN_ROWS
import com.example.mastermind.game.view.MARGIN_FROM_BORDER
import com.example.mastermind.game.view.MARGIN_TO_VERIFIER_ROW

class GuesserBoardArtist (guesserBoardViewImpl: GuesserBoardViewImpl){

    private val paint = Paint()
    private val config = guesserBoardViewImpl.config()
    private val resources = guesserBoardViewImpl.resources

    fun displayCurrentRow(canvas: Canvas?, currentRow: Int){
        canvas?.apply {
            config.apply {
                paint.color = CYAN
                val left = 0.0f
                val right = screenWidth.toFloat()
                val (top, bottom) = getYRowBoundriesFromRowIndex(currentRow)
                drawRect(left, top, right, bottom, paint)
            }
        }
    }

    fun drawDividingLines(canvas: Canvas?){
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

    fun drawGuessingArea(canvas: Canvas?, currentlyGuessedColors: IntArray, guessesSoFar: List<Array<Int>>){
        canvas?.apply {
            config.apply{
                for (i in 0 .. 15){
                    for (j in 0 until 4){
                        val rowIndex = 15 - i
                        paint.color = android.graphics.Color.GRAY
                        if(rowIndex < guessesSoFar.size) paint.color = guessesSoFar[rowIndex][j]
                        if(rowIndex == guessesSoFar.size) paint.color = currentlyGuessedColors[j]
                        val additionalSeparator = if(i >= 1) MARGIN_TO_VERIFIER_ROW - MARGIN_BETWEEN_ROWS else 0
                        val cx: Float = widthForVerification + spaceBetweenColors * (j + 1) + j * rowHeight + choiceRadius
                        val cy: Float = MARGIN_FROM_BORDER + additionalSeparator + (MARGIN_BETWEEN_ROWS + rowHeight) * i + choiceRadius
                        drawCircle(cx, cy, choiceRadius, paint)
                        if(i == 0){
                            paint.color = Color.BLACK
                            paint.textSize = resources.getDimensionPixelSize(R.dimen.questionMarkFontSize).toFloat()
                            drawText("?", 0, 1, cx - getQuestionMarkWidth() / 2, cy + getQuestionMarkHeight() / 2, paint)
                        }
                    }
                }
            }
        }
    }

    fun drawVerificationArea(canvas: Canvas?, verificationsSoFar: List<Array<Int>>){
        canvas?.apply {
            config.apply {
                for (i in 1 until 16){
                    for (m in 0 until 2){
                        for (n in 0 until 2) {
                            val j = (1 - m) * 2 + (1 - n)
                            val row = 15 - i
                            paint.color = android.graphics.Color.BLACK
                            if(row < verificationsSoFar.size) paint.color = verificationsSoFar[row][j]
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
}