package com.example.mastermind.game.view.touchable

import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import com.example.mastermind.R
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.view.GameBoardProvider
import com.example.mastermind.game.view.OnBoardTouchListener
import com.example.mastermind.game.view.verification_dialog.VerificationDialogView
import kotlin.properties.Delegates


abstract class LeftColorSelector(
    private val size: () -> Int,
    resources: Resources,
    private val centerOfColorSelector: SelectableCenterOfColorSelector
): OnBoardTouchListener.TouchableArea {

    override fun area(): Rect {
        val r = centerOfColorSelector.area()
        return Rect(r.left - size(), r.top, r.left,r.bottom)
    }

    override fun display(canvas: Canvas?) {
        if(!centerOfColorSelector.selected) return
        canvas?.apply {
            val prevPositionRect = area()
            val prevRect = Rect(0, 0, prevBmp.width, prevBmp.height)
            drawBitmap(prevBmp, prevRect, prevPositionRect, null)
        }
    }

    private val prevBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.back)

}


abstract class RightColorSelector (
    private val size: () -> Int,
    resources: Resources,
    private val centerOfColorSelector: SelectableCenterOfColorSelector
): OnBoardTouchListener.TouchableArea {

    private val nextBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.next)

    override fun area(): Rect {
        val r =centerOfColorSelector.area()
        return Rect(r.right, r.top, r.right + size(), r.bottom)
    }

    override fun display(canvas: Canvas?) {
        if(!centerOfColorSelector.selected) return
        canvas?.apply {
            val nextRect = Rect(0, 0, nextBmp.width, nextBmp.height)
            val nextPositionRect = area()
            drawBitmap(nextBmp, nextRect, nextPositionRect, null)
        }
    }

}


class SecretCombinationLeftColorSelector (
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val column: Int): LeftColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onPrevSecretColor(column)
    }

}


class SecretCombinationRightColorSelector (
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val column: Int): RightColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onNextSecretColor(column)
    }

}


class GuessedCombinationLeftColorSelector (
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val column: Int): LeftColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onPrevGuessedColor(column)
    }

}


class GuessedCombinationRightColorSelector (
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val column: Int): RightColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onNextGuessedColor(column)
    }

}


class CombinationSelector(
    private val leftColorSelector: LeftColorSelector,
    private val rightColorSelector: RightColorSelector,
    private val centerOfColorSelector: SelectableCenterOfColorSelector
): OnBoardTouchListener.TouchableArea {

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

abstract class SelectableCenterOfColorSelector(private val onSelected: () -> Unit):
    OnBoardTouchListener.TouchableArea {

    private val paint: Paint = Paint()
    var selected by Delegates.observable(false){
            _, _, _ -> onSelected()
    }

    override fun onTouched(event: MotionEvent) {
        selected = true
    }

    override fun dismiss() {
        selected = false
    }

    override fun display(canvas: Canvas?) {
        if(!selected) return
        canvas?.apply {
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            drawRect(area(), paint)
            paint.style = Paint.Style.FILL
        }
    }
}

class CenterOfColorSelector (
    private val boardProvider: GameBoardProvider,
    private val row: Int,
    private val column: Int,
    private val offset: Int
): SelectableCenterOfColorSelector(boardProvider::refresh) {

    override fun area(): Rect {
        boardProvider.config().apply {
            val (left, right) = getXColumnBoundriesFromColumnIndex(column, offset)
            val (top, bottom) = getYRowBoundriesFromRowIndex(row)
            return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }

}


class VerificationDialogCenterOfColorSelector(
    private val view: VerificationDialogView,
    private val index: Int
): SelectableCenterOfColorSelector(view::invalidate){

    override fun area(): Rect {
        view.config().apply {
            val p = choiceCenter(index)
            val r = Rect(
                (p.x - radius()).toInt(),
                (p.y - radius()).toInt(),
                (p.x + radius()).toInt(),
                (p.y + radius()).toInt()
            )
            return r
        }
    }
}


class VerificationDialogLeftColorSelector(
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val index: Int,
    private val view: VerificationDialogView
): LeftColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onPrevVerificationColor(index, view)
    }

}


class VerificationDialogRightColorSelector(
    size: () -> Int,
    resources: Resources,
    centerOfColorSelector: SelectableCenterOfColorSelector,
    private val presenter: GamePresenter?,
    private val index: Int,
    private val view: VerificationDialogView
): RightColorSelector(size, resources, centerOfColorSelector){

    override fun onTouched(event: MotionEvent) {
        presenter?.onNextVerificationColor(index, view)
    }

}