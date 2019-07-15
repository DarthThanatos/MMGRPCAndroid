package com.example.mastermind.game.view.touchable

import android.graphics.*
import android.view.MotionEvent
import com.example.mastermind.R
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.view.GameBoardProvider
import com.example.mastermind.game.view.OnBoardTouchListener
import com.example.mastermind.game.view.verification_dialog.VerificationDialogConfig
import com.example.mastermind.game.view.verification_dialog.VerificationDialogView

class AcceptSecretCombinationView(private val boardProvider: GameBoardProvider):
    OnBoardTouchListener.TouchableArea {

    override fun area(): Rect {
        boardProvider.config().apply {
            val (top, bottom) = getYRowBoundriesFromRowIndex(0)
            val margin = (widthForVerification - rowHeight) * 0.5f
            val left = widthForColors + margin
            val right = screenWidth - margin
            return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }

    override fun onTouched(event: MotionEvent) {
        boardProvider.presenter()?.onSecretAccepted()
    }

    override fun display(canvas: Canvas?) {
        canvas?.apply {
            val bmpArea = Rect(0, 0, submitBmp.width, submitBmp.height)
            drawBitmap(submitBmp, bmpArea, area(), null)

        }
    }

    private val submitBmp: Bitmap = BitmapFactory.decodeResource(boardProvider.resources(), R.drawable.submit)
}

class AcceptGuessedCombination(private val boardProvider: GameBoardProvider, private val row: Int):
    OnBoardTouchListener.TouchableArea {

    override fun area(): Rect {
        boardProvider.config().apply {
            val (top, bottom) = getYRowBoundriesFromRowIndex(row)
            val margin = (widthForVerification - rowHeight) * 0.5f
            val left = margin
            val right = widthForVerification - margin
            return Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }

    override fun onTouched(event: MotionEvent) {
        boardProvider.presenter()?.onGuessAccepted()
    }

    override fun display(canvas: Canvas?) {
        canvas?.apply {
            val bmpArea = Rect(0, 0, submitBmp.width, submitBmp.height)
            drawBitmap(submitBmp, bmpArea, area(), null)
        }
    }

    private val submitBmp: Bitmap = BitmapFactory.decodeResource(boardProvider.resources(), R.drawable.submit)
}

class AcceptVerificationMarkers(private val view: VerificationDialogView): OnBoardTouchListener.TouchableArea{

    override fun area(): Rect {
        view.config().apply {
            return Rect(
                submitLeft(), submitTop(), submitRight(), submitBottom()
            )
        }
    }

    override fun onTouched(event: MotionEvent) {
        view.presenter?.onVerificationAccepted()
    }

    override fun display(canvas: Canvas?) {
        canvas?.apply {
            val bmpArea = Rect(0, 0, submitBmp.width, submitBmp.height)
            drawBitmap(submitBmp, bmpArea, area(), null)
        }
    }

    private val submitBmp: Bitmap = BitmapFactory.decodeResource(view.resources, R.drawable.submit)

}