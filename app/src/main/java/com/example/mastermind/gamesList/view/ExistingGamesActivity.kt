package com.example.mastermind.gamesList.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.game.view.GameActivity
import com.example.mastermind.R
import com.example.mastermind.constants.*
import com.example.mastermind.gamesList.presenter.GamesListPresenter
import com.example.mastermind.gamesList.presenter.GamesListPresenterImpl
import kotlinx.android.synthetic.main.activity_existing_games.*
import server.GameDescription
import server.GamesByName
import java.util.*

interface ExisitingGamesView{
    fun turnOnProgress()
    fun turnOffProgress()
    fun askIfShouldJoinGame(gameDescription: GameDescription)
    fun displayGames(gamesByName: GamesByName)
}

class ExistingGamesActivity : AppCompatActivity(), ExisitingGamesView, JoinGameDialog.JoinGameDialogListener {

    private lateinit var presenter: GamesListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_games)
        initPresenter()
        presenter.getGamesByName()
    }

    private fun initPresenter(){
        val hostName = intent.getStringExtra(HOST_NAME_KEY)
        val gameName = intent.getStringExtra(GAME_NAME_KEY)
        presenter = GamesListPresenterImpl(host=hostName, gameName=gameName)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun displayGames(gamesByName: GamesByName) {
        Log.d(ExistingGamesActivity::class.java.simpleName, gamesByName.toString())
        val linearLayoutManager = LinearLayoutManager(this)
        gamesListView.layoutManager = linearLayoutManager
        val decorator = DividerItemDecoration(this, linearLayoutManager.orientation)
        gamesListView.addItemDecoration(decorator)
        gamesListView.adapter = GamesListAdapter(gamesByName, this)
    }

    override fun turnOnProgress() {
        gamesListProgressBar.visibility = View.VISIBLE
    }

    override fun turnOffProgress() {
        gamesListProgressBar.visibility = View.INVISIBLE
    }

    override fun onDialogPositiveClick(gameId: UUID) {
        val userName = intent.getStringExtra(USER_NAME_KEY)
        val hostName = intent.getStringExtra(HOST_NAME_KEY)
        val gameName = intent.getStringExtra(GAME_NAME_KEY)
        val gameIntent = Intent(this, GameActivity::class.java)
        gameIntent.putExtra(HOST_NAME_KEY, hostName)
        gameIntent.putExtra(GAME_NAME_KEY, gameName)
        gameIntent.putExtra(USER_NAME_KEY, userName)
        gameIntent.putExtra(GAME_ID_KEY, gameId.toString())
        startActivity(gameIntent)
    }

    override fun askIfShouldJoinGame(gameDescription: GameDescription) {
        val newFragmentDialog = JoinGameDialog(gameDescription)
        newFragmentDialog.show(supportFragmentManager, JOIN_GAME_TAG)
    }

}
