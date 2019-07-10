package com.example.mastermind.game.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import server.Color

interface VerifierBoardView{
    fun getSecretCombinationColorArr(): Array<Color>
}

class VerifierBoardViewImpl(context: Context, attrSet: AttributeSet): View(context, attrSet), VerifierBoardView{

    private val metrics = DisplayMetrics()
    private val ROWS_AMOUNT = 16
    private val GUESS_AREA_PROPORTION = 3
    private val RIGHT_AREA_PROPORTION = 1
    private val SECRET_COMBINATION_AREA_PROPORTION = 3
    private val SPACE_BETWEEN_ROWS = 10
    private val SPACE_BETWEEN_VERIFIER_ROW = 20
    private val MARGIN = 10
    private var rowHeight = 0
    private var columnWidth = 0
    private var screenWidth = 0
    private var screenHeight = 0
    private val paint = Paint()
    init{
        val gameActivity = (context as com.example.mastermind.game.view.GameActivity)
        gameActivity.windowManager.defaultDisplay.getMetrics(metrics)
        screenWidth = metrics.widthPixels
        var resourceId = gameActivity.resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusHeight = if(resourceId > 0) gameActivity.resources.getDimensionPixelSize(resourceId) else 0
        val styledAttrs = gameActivity.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarHeight: Int = styledAttrs.getDimension(0, 0.0f).toInt()
        resourceId = gameActivity.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        val navigationBarHeight = if(resourceId > 0) gameActivity.resources.getDimensionPixelSize(resourceId) else 0
        screenHeight = metrics.heightPixels - actionBarHeight - statusHeight
        val totalMargin = 2*MARGIN + (ROWS_AMOUNT - 2) * SPACE_BETWEEN_ROWS + SPACE_BETWEEN_VERIFIER_ROW
        rowHeight = (screenHeight - totalMargin) / ROWS_AMOUNT
        paint.color = android.graphics.Color.BLUE
    }


    private class onTouch: OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            v?.performClick()
            return true
        }

    }

    override fun getSecretCombinationColorArr(): Array<Color> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            for (i in 0 until 15){
                for (j in 0 until 4){
                    val widthForColors = screenWidth * 1.0 * GUESS_AREA_PROPORTION / (GUESS_AREA_PROPORTION + RIGHT_AREA_PROPORTION)
                    val spaceBetweenColors = (widthForColors - 4 * rowHeight) / 5.0
                    val cx: Float = (spaceBetweenColors * (j + 1) + j * rowHeight + 0.5 * rowHeight).toFloat()
                    val cy: Float = (MARGIN + (SPACE_BETWEEN_ROWS + rowHeight) * i + 0.5 * rowHeight).toFloat()
                    drawCircle(cx, cy, (0.5 * rowHeight).toFloat(), paint)
                }
            }

            for (j in 0 until 4){
                val widthForColors = screenWidth * 1.0 * GUESS_AREA_PROPORTION / (GUESS_AREA_PROPORTION + RIGHT_AREA_PROPORTION)
                val spaceBetweenColors = (widthForColors - 4 * rowHeight) / 5.0
                val cx: Float = (spaceBetweenColors * (j + 1) + j * rowHeight + 0.5 * rowHeight).toFloat()
                val cy: Float = (MARGIN + SPACE_BETWEEN_VERIFIER_ROW + (SPACE_BETWEEN_ROWS + rowHeight) * 15 + 0.5 * rowHeight).toFloat()
                drawCircle(cx, cy, (0.5 * rowHeight).toFloat(), paint)
            }
        }
    }

}