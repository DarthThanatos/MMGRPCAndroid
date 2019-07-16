package com.example.mastermind.game.view.verification_dialog

import android.graphics.Point
import android.graphics.Rect


class VerificationDialogConfig(private val view: VerificationDialogView) {

    fun radius() = view.height * 0.5 / 8

    private fun spaceBetweenCombinationElements()
        = (view.width - 8 * radius()) * 0.75 / 3

    private fun horizontalMarginFromCombinationToBorder()
        = (view.width - 8 * radius()) * 0.25 / 2

    fun horizontalMarginBetweenChoices()
        = verticalMarginBetweenChoices()

    private fun horizontalMarginFromChoicesToBorder()
        = (view.width - horizontalMarginBetweenChoices() - 4 * radius()) / 2

    private fun verticalMarginToBorder()
        = view.height * 0.5 * 0.125

    private fun verticalMarginBetweenGroups()
        = view.height * 0.5 * 0.6 / 2

    private fun verticalMarginBetweenChoices()
        = view.height * 0.5 * 0.15


    fun submitLeft(): Int
        = ((view.width - 2 * radius()) / 2).toInt()

    fun submitRight(): Int
        = (submitLeft() + 2 * radius()).toInt()

    fun submitBottom(): Int
        = view.height - verticalMarginToBorder().toInt()

    fun submitTop(): Int
        = (submitBottom() - 2 * radius()).toInt()

    private fun pointWithOffset(point: Point): Point{
        val offsets = intArrayOf(-1, -1)
        view.getLocationOnScreen(offsets)
        return Point(point.x + offsets[0], point.y + offsets[1])
    }

    fun centerOfCombinationElement(index: Int, includeOffset: Boolean = false): Point{
        val x = horizontalMarginFromCombinationToBorder() + (2 * radius() + spaceBetweenCombinationElements())* index + radius()
        val y = verticalMarginToBorder() + radius()
        val p = Point(x.toInt(), y.toInt())
        return if(includeOffset) pointWithOffset(p) else p
    }

    fun choiceCenter(index: Int, includeOffset: Boolean = false): Point{
        val i = index / 2
        val j = index % 2
        val x = horizontalMarginFromChoicesToBorder() + (2 * radius() + horizontalMarginBetweenChoices()) * j + radius()
        val y = verticalMarginToBorder() + 2 * radius() + verticalMarginBetweenGroups() + (2 * radius() + verticalMarginBetweenChoices()) * i + radius()
        val p = Point(x.toInt(), y.toInt())
        return if(includeOffset) pointWithOffset(p) else p
    }

    fun inputRect(): Rect {
        val margin = 5
        val left = horizontalMarginFromChoicesToBorder() - horizontalMarginBetweenChoices() - margin
        val right = left + 3 * horizontalMarginBetweenChoices() + 4 * radius() + 2*margin
        val top = verticalMarginToBorder() + 2 * radius() + verticalMarginBetweenGroups() - margin
        val bottom = top + 2* margin  + 4 * radius() + verticalMarginBetweenChoices()
        return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
    }
}
