package com.example.mastermind.game.view

import android.graphics.*
import android.view.MotionEvent
import com.example.mastermind.R
import kotlin.properties.Delegates


abstract class LeftColorSelector(
    private val boardProvider: GameBoardProvider,
    private val row: Int,
    private val column: Int,
    private val offset: Int,
    private val centerOfColorSelector: CenterOfColorSelector
): OnBoardTouchListener.TouchableArea {

    override fun area(): Rect {
        boardProvider.config().apply {
            val (left, _) = getXColumnBoundriesFromColumnIndex(column, offset)
            val (top, bottom) = getGuesserYRowBoundriesFromRowIndex(row)
            return Rect((left - spaceBetweenColors).toInt(), top.toInt(), left.toInt(), bottom.toInt())
        }
    }

    override fun display(canvas: Canvas?) {
        if(!centerOfColorSelector.selected) return
        val config = boardProvider.config()
        canvas?.apply {
            config.apply {
                val prevPositionRect = area()
                val prevRect = Rect(0, 0, prevBmp.width, prevBmp.height)
                drawBitmap(prevBmp, prevRect, prevPositionRect, null)
            }
        }
    }

    private val prevBmp: Bitmap = BitmapFactory.decodeResource(boardProvider.resources(), R.drawable.back)

}

abstract class RightColorSelector(
    private val boardProvider: GameBoardProvider,
    private val row: Int,
    private val column: Int,
    private val offset: Int,
    private val centerOfColorSelector: CenterOfColorSelector
): OnBoardTouchListener.TouchableArea {

    private val nextBmp: Bitmap = BitmapFactory.decodeResource(boardProvider.resources(), R.drawable.next)

    override fun area(): Rect {
        boardProvider.config().apply {
            val (_, right) = getXColumnBoundriesFromColumnIndex(column, offset)
            val (top, bottom) = getGuesserYRowBoundriesFromRowIndex(row)
            return Rect(right.toInt(), top.toInt(), (right + spaceBetweenColors).toInt(), bottom.toInt())

        }
    }

    override fun display(canvas: Canvas?) {
        if(!centerOfColorSelector.selected) return
        val config = boardProvider.config()
        canvas?.apply {
            config.apply {
                val nextRect = Rect(0, 0, nextBmp.width, nextBmp.height)
                val nextPositionRect = area()
                drawBitmap(nextBmp, nextRect, nextPositionRect, null)
            }
        }
    }

}

class SecretCombinationRightColorSelector (
    private val boardProvider: GameBoardProvider,
    row: Int,
    private val column: Int,
    offset: Int,
    centerOfColorSelector: CenterOfColorSelector): RightColorSelector(boardProvider, row, column, offset, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        boardProvider.presenter()?.onNextSecretColor(column)
    }

}

class SecretCombinationLeftColorSelector(
    private val boardProvider: GameBoardProvider,
    row: Int,
    private val column: Int,
    offset: Int,
    centerOfColorSelector: CenterOfColorSelector): LeftColorSelector(boardProvider, row, column, offset, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        boardProvider.presenter()?.onPrevSecretColor(column)
    }

}

class CombinationSelector(
    private val leftColorSelector: LeftColorSelector,
    private val rightColorSelector: RightColorSelector,
    private val centerOfColorSelector: CenterOfColorSelector
): OnBoardTouchListener.TouchableArea{

    override fun area(): Rect {
        val leftArea = leftColorSelector.area()
        val rightArea = rightColorSelector.area()
        return Rect(leftArea.left, leftArea.top, rightArea.right, rightArea.bottom)
    }

    private val children = listOf(leftColorSelector, rightColorSelector, centerOfColorSelector)

    override fun onTouched(event: MotionEvent) {
        val centerSelected = centerOfColorSelector.area().contains(event.x.toInt(), event.y.toInt()) || centerOfColorSelector.selected
        if(!centerSelected) return
        for(child in children){
            child.apply {
                if(area().contains(event.x.toInt(), event.y.toInt()))
                    onTouched(event)
            }
        }

    }

    override fun dismiss() {
        for(child in children){
            child.dismiss()
        }
    }

    override fun display(canvas: Canvas?) {
        for(child in children){
            child.display(canvas)
        }
    }

}

class CenterOfColorSelector (
    private val boardProvider: GameBoardProvider,
    private val row: Int,
    private val column: Int,
    private val offset: Int
): OnBoardTouchListener.TouchableArea {

    private val paint: Paint = Paint()
    var selected by Delegates.observable(false){
        _, _, _ -> boardProvider.refresh()
    }

    override fun area(): Rect {
        boardProvider.config().apply {
            val (left, right) = getXColumnBoundriesFromColumnIndex(column, offset)
            val (top, bottom) = getGuesserYRowBoundriesFromRowIndex(row)
            return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }

    override fun display(canvas: Canvas?) {
        if(!selected) return
        val config = boardProvider.config()
        canvas?.apply {
            config.apply {
                paint.color = Color.BLACK
                paint.style = Paint.Style.STROKE
                drawRect(area(), paint)
                paint.style = Paint.Style.FILL
            }
        }
    }

    override fun onTouched(event: MotionEvent) {
        selected = true
    }

    override fun dismiss() {
        selected = false
    }
}
