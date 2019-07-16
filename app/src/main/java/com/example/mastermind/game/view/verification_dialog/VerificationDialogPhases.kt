package com.example.mastermind.game.view.verification_dialog

import com.example.mastermind.game.view.OnBoardTouchListener
import com.example.mastermind.game.view.touchable.*

class VerificationDialogPhases (private val view: VerificationDialogView){

    fun verificationInit(): MutableList<OnBoardTouchListener.TouchableArea>{
        val touchableAreas = mutableListOf<OnBoardTouchListener.TouchableArea>()
        for (j in 0 until 4){
            val centerOfColorSelector = VerificationDialogCenterOfColorSelector(view, j)
            val arrowSize = { view.config().horizontalMarginBetweenChoices().toInt() }
            val leftColorSelector = VerificationDialogLeftColorSelector(arrowSize, view.resources, centerOfColorSelector, view.presenter, j, view)
            val rightColorSelector = VerificationDialogRightColorSelector(arrowSize, view.resources, centerOfColorSelector, view.presenter, j, view)
            val compundSelector = CombinationSelector(leftColorSelector, rightColorSelector, centerOfColorSelector)
            touchableAreas.add(compundSelector)
        }
        return touchableAreas
    }

    fun verificationWithAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>): MutableList<OnBoardTouchListener.TouchableArea>{
        val verificationTouchableAreas = touchableAreas.filter { it !is AcceptVerificationMarkers }.toMutableList()
        verificationTouchableAreas.add(AcceptVerificationMarkers(view))
        return verificationTouchableAreas
    }

    fun verificationWithoutAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>) =
        touchableAreas.filter { it !is AcceptVerificationMarkers }.toMutableList()
}