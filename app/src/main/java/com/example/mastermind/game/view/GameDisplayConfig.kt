package com.example.mastermind.game.view

val COLORS_NUMBER = 4

val MARGIN_BETWEEN_ROWS = 10
val MARGIN_TO_VERIFIER_ROW = 20
val MARGIN_FROM_BORDER = 10

val ROWS_AMOUNT = 16
val TOTAL_MARGIN = 2 * MARGIN_FROM_BORDER + (ROWS_AMOUNT - 2) * MARGIN_BETWEEN_ROWS + MARGIN_TO_VERIFIER_ROW
val SPACES_NUMBER_IN_COLOR_AREA = 5 // 3 between colors + 1 at start + 1 at the end

data class GameDisplayConfig(val screenWidth: Int, val screenHeight: Int){
    val rowHeight = (screenHeight - TOTAL_MARGIN) / ROWS_AMOUNT
    val choiceRadius = 0.5f * rowHeight

    val widthForColors = screenWidth * 0.75f
    val widthForVerification = screenWidth * 0.25f

    val verticalMarkersGap = rowHeight * 0.05f
    val verificationMarkerRadius = 0.125f * rowHeight
    val verticalBorderMargin = (rowHeight - 4 * verificationMarkerRadius - verticalMarkersGap) * 0.5f

    private val horizontalSpaceForMarkers = screenWidth * 0.25f - 4 * verificationMarkerRadius
    val horizontalMarkersGap = horizontalSpaceForMarkers * 0.05f
    val horizontalBorderMargin = (horizontalSpaceForMarkers - horizontalMarkersGap) * 0.5f

    private val widthForChoices = COLORS_NUMBER * rowHeight
    val spaceBetweenColors = (widthForColors - widthForChoices) / SPACES_NUMBER_IN_COLOR_AREA
}