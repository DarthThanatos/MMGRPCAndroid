package com.example.mastermind.game.view.guesser_board

import com.example.mastermind.game.view.GameBoardProvider
import com.example.mastermind.game.view.OnBoardTouchListener
import com.example.mastermind.game.view.touchable.*

class GuesserBoardPhases(private val boardProvider: GameBoardProvider) {

    fun guessingCombinationSpecificationPhase(currentRow: Int): MutableList<OnBoardTouchListener.TouchableArea>{
        val offset = boardProvider.touchOffset()
        val touchableAreas = mutableListOf<OnBoardTouchListener.TouchableArea>()
        val size = boardProvider.config().spaceBetweenColors
        for (j in 0 until 4){
            val centerOfColorSelector =
                CenterOfColorSelector(boardProvider, currentRow, j, offset)
            val leftColorSelector = GuessedCombinationLeftColorSelector(
                { size.toInt() },
                boardProvider.resources(),
                centerOfColorSelector,
                boardProvider.presenter(),
                j
            )
            val rightColorSelector = GuessedCombinationRightColorSelector(
                { size.toInt() },
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
            touchableAreas.add(compundSelector)
        }
        return touchableAreas
    }

    fun guessingCombinationWithAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>, currentRow: Int): MutableList<OnBoardTouchListener.TouchableArea>{
        val guessBoardTouchableAreas = touchableAreas.filter { it !is AcceptGuessedCombination }.toMutableList()
        guessBoardTouchableAreas.add(
            AcceptGuessedCombination(
                boardProvider,
                currentRow
            )
        )
        return guessBoardTouchableAreas
    }


    fun guessingSpecificationWithoutAcceptView(touchableAreas: MutableList<OnBoardTouchListener.TouchableArea>) =
        touchableAreas.filter { it !is AcceptGuessedCombination }.toMutableList()

    fun waitPhase() = mutableListOf<OnBoardTouchListener.TouchableArea>()

}