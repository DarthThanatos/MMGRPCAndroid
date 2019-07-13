package com.example.mastermind.game.view

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLACK
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.mastermind.R
import server.Color
import kotlin.properties.Delegates

interface GuesserBoardView{
    fun getCombinationColorArr(): Array<Color>
    fun displayCombinations(previousCombinations: Array<Color>)
}

class GuesserBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet), GuesserBoardView{

    override fun displayCombinations(previousCombinations: Array<Color>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCombinationColorArr(): Array<Color> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val paint = Paint()
    private val screenCalculator = ScreenCalculator(context as Activity)
    private val config = GameDisplayConfig(screenCalculator.screenWidth, screenCalculator.screenHeight)

    private var currentRow by Delegates.observable(0){
        _, _, _ -> invalidate()
    }

    init{
        setOnTouchListener(OnColorTouchListener(config, config.widthForVerification.toInt(), this::onColorTouched) { currentRow })
    }

    private fun onColorTouched(j: Int){
        Toast.makeText(context, "touched color number: $j", Toast.LENGTH_LONG).show()
        currentRow = if(currentRow <14) currentRow + 1 else 0
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

    private fun drawCurrentRowRect(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = android.graphics.Color.CYAN
                val left = 0.0f
                val top = (MARGIN_FROM_BORDER + rowHeight + MARGIN_TO_VERIFIER_ROW + (15 - currentRow - 1) * (rowHeight + MARGIN_BETWEEN_ROWS)).toFloat()
                val right = screenWidth.toFloat()
                val bottom = top + 2 * choiceRadius
                drawRect(left, top, right, bottom, paint)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        drawCurrentRowRect(canvas)
        drawGuessingArea(canvas)
        drawVerificationArea(canvas)
        drawDividingLines(canvas)
    }
}