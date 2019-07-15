package com.example.mastermind.game.view.verification_dialog

import android.graphics.Canvas
import android.graphics.Paint

class VerificationDialogArtist(private val config: VerificationDialogConfig){

    private val paint = Paint()

    fun drawCombination(canvas: Canvas?, combinationArray: Array<Int>){
        canvas?.apply{
            config.apply {
                for (j in 0 until 4){
                    paint.color = combinationArray[j]
                    val center = centerOfCombinationElement(j)
                    drawCircle(center.x.toFloat(), center.y.toFloat(), radius().toFloat(), paint)
                }
            }
        }
    }

    fun drawInput(canvas: Canvas?, verificationColors: IntArray){
        canvas?.apply {
            config.apply {
                for(j in 0 until 4){
                    paint.color = verificationColors[j]
                    val center = choiceCenter(j)
                    drawCircle(center.x.toFloat(), center.y.toFloat(), radius().toFloat(), paint)
                }
            }
        }
    }
}