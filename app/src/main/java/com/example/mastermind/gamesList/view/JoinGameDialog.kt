package com.example.mastermind.gamesList.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import server.GameDescription
import java.lang.ClassCastException
import java.lang.IllegalStateException
import java.util.*

class JoinGameDialog(private val gameDescription: GameDescription): DialogFragment(){

    internal lateinit var listener: JoinGameDialogListener

    interface JoinGameDialogListener{
        fun onDialogPositiveClick(gameId: UUID)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            listener = context as JoinGameDialogListener
        }catch (e: ClassCastException){
            throw ClassCastException("${context.toString()} must implement JoinGameDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = activity?.let{
        val builder = AlertDialog.Builder(it)
        builder.setMessage("Join game ${gameDescription.gameName} with id: ${gameDescription.gameId}, created: ${gameDescription.creationDate}? ")
            .setPositiveButton("Join", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    listener.onDialogPositiveClick(UUID.fromString(gameDescription.gameId))
                }
            })
            .setNegativeButton("Cancel", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            }).create()
    } ?: throw IllegalStateException("Activity cannot be null")

}