package com.example.mastermind.game.view.verification_dialog

import android.graphics.Canvas
import android.graphics.Color.BLACK
import android.graphics.Paint

class VerificationDialogArtist(private val config: VerificationDialogConfig){

    private val paint = Paint()

    fun drawCombination(canvas: Canvas?, combinationArray: Array<Int>){
        canvas?.apply{
            config.apply {
                for (j in 0 until 4){
                    paint.color = combinationArray[3 - j]
                    val center = centerOfCombinationElement(j)
                    drawCircle(center.x.toFloat(), center.y.toFloat(), radius().toFloat(), paint)
                }
            }
        }
    }

    fun drawInputRect(canvas: Canvas?){
        canvas?.apply {
            config.apply {
                paint.color = BLACK
                paint.style = Paint.Style.STROKE
                val r = inputRect()
                drawRect(r, paint)
                paint.style = Paint.Style.FILL
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