package com.example.mastermind.game.view.verification_dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.mastermind.R
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.view.OnBoardTouchListener
import kotlinx.android.synthetic.main.verification_dialog.view.*
import java.lang.IllegalStateException
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class VerificationDialogView(context: Context, attributeSet: AttributeSet): View(context, attributeSet){

    var presenter: GamePresenter? = null

    private val config = VerificationDialogConfig(this)
    fun config() = config

    var combinationArray: Array<Int> by Delegates.observable(arrayOf(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY), this::onFieldChanged)
    var verificationMarkersColors by Delegates.observable(intArrayOf(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY), this::onFieldChanged)

    @Suppress("UNUSED_PARAMETER")
    private fun <T> onFieldChanged(prop: KProperty<*>, old: T, new: T){
        invalidate()
    }

    val listener = OnBoardTouchListener(this)
    private val phases = VerificationDialogPhases(this)

    init {
        setOnTouchListener(listener)
        listener.touchableAreas = phases.verificationInit()
    }

    private val artist = VerificationDialogArtist(config)

    fun updateVerificationMarkers(verificationMarkersColors: Array<Int>){
        this.verificationMarkersColors = verificationMarkersColors.copyOf().toIntArray()
    }

    fun showAcceptButton(){
        listener.touchableAreas = phases.verificationWithAcceptView(listener.touchableAreas)
    }

    fun hideAcceptButton(){
        listener.touchableAreas = phases.verificationWithoutAcceptView(listener.touchableAreas)
    }

    override fun onDraw(canvas: Canvas?) {
        artist.apply {
            drawCombination(canvas, combinationArray)
            drawInput(canvas, verificationMarkersColors)
        }
        for(touchableArea in listener.touchableAreas){
            touchableArea.display(canvas)
        }
    }
}

class VerificationDialog(private val presenter: GamePresenter?, private val combination: Array<Int>): DialogFragment(){

    @SuppressLint("InflateParams")
    private fun setupView(inflater: LayoutInflater): View{
        val view = inflater.inflate(R.layout.verification_dialog, null)
        view.verificationDialogView.presenter = presenter
        view.verificationDialogView.combinationArray  = combination
        dialog.setCanceledOnTouchOutside(false)
//        dialog.window.decorView.setOnTouchListener(view.verificationDialogView.listener)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setView(setupView(requireActivity().layoutInflater))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}