package com.example.mastermind.game.view.verifier_board

import com.example.mastermind.game.view.GameBoardProvider
import com.example.mastermind.game.view.OnBoardTouchListener
import com.example.mastermind.game.view.touchable.*

class VerifierBoardPhases(private val boardProvider: GameBoardProvider){

    fun secretSpecificationInit(): MutableList<OnBoardTouchListener.TouchableArea>{
        val secretSelectionTouchableAreas = mutableListOf<OnBoardTouchListener.TouchableArea>()
        val size = boardProvider.config().spaceBetweenColors
        for (j in 0 until 4){
            val centerOfColorSelector =
                CenterOfColorSelector(boardProvider, 0, j, 0)
            val leftColorSelector = SecretCombinationLeftColorSelector(
                size.toInt(),
                boardProvider.resources(),
                centerOfColorSelector,
                boardProvider.presenter(),
                j
            )
            val rightColorSelector = SecretCombinationRightColorSelector(
                size.toInt(),
                boardProvider.resources(),
                centerOfColorSelector,
                boardProvider.presenter(),
                j
            )
            val compundSelector = CombinationSelector(
                leftColorSelector,
                rightColorSelector,
                centerOfColorSelector
            )
            secretSelectionTouchableAreas.add(compundSelector)
        }
        return secretSelectionTouchableAreas
    }

    fun secretSpecificationWithAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>): MutableList<OnBoardTouchListener.TouchableArea>{
        val secretSelectionTouchableAreas = touchableAreas.filter { it !is AcceptSecretCombinationView }.toMutableList()
        secretSelectionTouchableAreas.add(
            AcceptSecretCombinationView(
                boardProvider
            )
        )
        return secretSelectionTouchableAreas
    }

    fun secretSpecificationWithoutAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>) =
        touchableAreas.filter { it !is AcceptSecretCombinationView }.toMutableList()

    fun waitPhase() = mutableListOf<OnBoardTouchListener.TouchableArea>()

}