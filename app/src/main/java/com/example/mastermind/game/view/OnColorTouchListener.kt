package com.example.mastermind.game.view

import android.view.MotionEvent
import android.view.View

class OnColorTouchListener(private val config: GameDisplayConfig, private val offset: Int, private val onColorTouched: (Int)->Unit, private val getRowOfInterest: () -> Int): View.OnTouchListener{

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val row = getRowOfInterest()
        config.apply {
            val yMin = MARGIN_FROM_BORDER + rowHeight + MARGIN_TO_VERIFIER_ROW + (15 - row - 1) * (rowHeight + MARGIN_BETWEEN_ROWS)
            val yMax = yMin + rowHeight + MARGIN_BETWEEN_ROWS
            if(event.y <= yMax && event.y >= yMin){
                for (j in 0 until 4){
                    val xMin = offset + j * (spaceBetweenColors + 2 * choiceRadius) + spaceBetweenColors
                    val xMax = xMin + 2 * choiceRadius
                    if(event.x <= xMax && event.x >= xMin){
                        onColorTouched(j)
                    }
                }
            }
        }
        v.performClick()
        return false
    }


}