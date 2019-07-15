package com.example.mastermind.game.view.verification_dialog

import android.graphics.Point


class VerificationDialogConfig(private val view: VerificationDialogView) {

    private fun verticalMarginToBorder()
        = view.height * 0.25 * 0.125

    private fun spaceBetweenCombinationElements()
        = (view.width - 8 * radius()) * 0.75 / 3

    private fun horizontalMarginFromCombinationToBorder()
        = (view.width - 8 * radius()) * 0.25 / 2

    fun centerOfCombinationElement(index: Int): Point{
        val x = horizontalMarginFromCombinationToBorder() + (2 * radius() + spaceBetweenCombinationElements())* index + radius()
        val y = verticalMarginToBorder() + radius()
        return Point(x.toInt(), y.toInt())
    }

    fun horizontalMarginBetweenChoices()
        = (view.width - 4 * radius()) * 0.5

    private fun horizontalMarginFromChoicesToBorder()
        = (view.width - 4 * radius()) * 0.5 / 2

    private fun verticalMarginBetweenGroups()
        = view.height * 0.25 * 0.2

    private fun verticalMarginBetweenChoices()
        = view.height * 0.25 * 0.05

    private fun submitRadius()
        = view.height * 0.25 * 0.5

    fun submitLeft(): Int
        = ((view.width - 2 * submitRadius()) / 2).toInt()

    fun submitRight(): Int
        = (submitLeft() + 2 * submitRadius()).toInt()

    fun submitBottom(): Int
        = verticalMarginToBorder().toInt()

    fun submitTop(): Int
        = (submitBottom() - 2 * submitRadius()).toInt()

    fun radius()
        = view.height * 0.75 / 6

    fun choiceCenter(index: Int): Point{
        val i = index / 2
        val j = index % 2
        val x = horizontalMarginFromChoicesToBorder() + (2 * radius() + horizontalMarginBetweenChoices()) * j + radius()
        val y = verticalMarginToBorder() + 2 * radius() + verticalMarginBetweenGroups() + (2 * radius() + verticalMarginBetweenChoices()) * i + radius()
        return Point(x.toInt(), y.toInt())
    }
}
