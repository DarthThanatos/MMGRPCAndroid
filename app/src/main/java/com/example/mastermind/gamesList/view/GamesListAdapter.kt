package com.example.mastermind.gamesList.view

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.extention.inflate
import kotlinx.android.synthetic.main.games_list_item.view.*
import kotlinx.android.synthetic.main.list_header.view.*
import server.GameDescription
import server.GamesByName

class GamesListAdapter(private val gamesByName: GamesByName): RecyclerView.Adapter<GamesListAdapter.GameHolder>(){

    private val HEADER_TYPE = 1

    class GameHolder(private val v: View): RecyclerView.ViewHolder(v){

        @SuppressLint("SetTextI18n")
        private fun bindHeader(gamesByName: GamesByName){
            v.game_list_header.setText("Results for: ${gamesByName.name}")
        }

        private fun bindItem(gameDescription: GameDescription){
            v.gameNameTextViewIntro.setText(gameDescription.gameName)
            v.gameIdTextView.setText(gameDescription.gameId)
            v.creationDateTextView.setText(gameDescription.creationDate)
            v.guesserNameTextView.setText(gameDescription.guesser.playerName)
            v.guesserIdTextView.setText(gameDescription.guesser.playerId)
            v.verifierNameTextView.setText(gameDescription.verifier.playerName)
            v.verifierIdTextView.setText(gameDescription.verifier.playerId)

        }

        fun bindGame(gameDescription: GameDescription, gamesByName: GamesByName, position: Int){
            if (position == 0){
                bindHeader(gamesByName)
            }
            else {
                bindItem(gameDescription)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val viewLayout = if(viewType == HEADER_TYPE) R.layout.list_header else R.layout.games_list_item
        val inflatedView = parent.inflate(viewLayout, false)
        return GameHolder(inflatedView)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return HEADER_TYPE
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = gamesByName.gamesCount + 1

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val index = if(position == 0) 0 else position - 1
        holder.bindGame(gamesByName.gamesList[index], gamesByName, position)
    }

}