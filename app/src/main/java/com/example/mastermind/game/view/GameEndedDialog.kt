package com.example.mastermind.game.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class GameEndedDialog(private val title: String, private val msg: String, private val onClose: () -> Unit): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?) = activity?.let{
        val builder = AlertDialog.Builder(it)
        builder.setMessage(msg).setTitle(title)
            .setPositiveButton("Close", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    onClose()
                }
            }).create()
    } ?: throw IllegalStateException("Activity cannot be null")
}