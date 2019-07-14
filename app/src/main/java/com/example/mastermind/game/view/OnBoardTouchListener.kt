package com.example.mastermind.game.view

import android.graphics.Canvas
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import kotlin.properties.Delegates

class OnBoardTouchListener(private val boardProvider: GameBoardProvider): View.OnTouchListener{

    var touchableAreas by Delegates.observable(mutableListOf<TouchableArea>()) {
        _, _, _ -> boardProvider.refresh()
    }

    interface TouchableArea{
        fun area(): Rect
        fun onTouched(event: MotionEvent)
        fun display(canvas: Canvas?)
        fun dismiss(){}
    }


    private fun dismissiveLoop(event: MotionEvent){
        for(touchableArea in touchableAreas) {
            touchableArea.apply {
                if (!area().contains(event.x.toInt(), event.y.toInt())) {
                    dismiss()
                }
            }
        }
    }

    private fun activationLoop(event: MotionEvent){
        for(touchableArea in touchableAreas) {
            touchableArea.apply {
                if (area().contains(event.x.toInt(), event.y.toInt())) {
                    onTouched(event)
                }
            }
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        dismissiveLoop(event)
        activationLoop(event)
        v.performClick()
        return false
    }

}