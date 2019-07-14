package com.example.mastermind.game.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent

class AcceptSecretCombinationView(private val boardProvider: GameBoardProvider): OnBoardTouchListener.TouchableArea{

    private val paint = Paint()

    override fun area(): Rect {
        boardProvider.config().apply {
            val (top, bottom) = getGuesserYRowBoundriesFromRowIndex(0)
            val margin = 10
            val left = widthForColors + margin
            val right = screenWidth - margin
            return Rect(left.toInt(), top.toInt(), right, bottom.toInt())
        }
    }

    override fun onTouched(event: MotionEvent) {
        println("clicked")
    }

    override fun display(canvas: Canvas?) {
        canvas?.apply {
            val rect = area()
            paint.color = Color.MAGENTA
            drawRect(rect, paint)

        }
    }

}