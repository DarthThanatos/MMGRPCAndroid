package com.example.mastermind.gamesList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.R
import com.example.mastermind.constants.GAME_NAME_KEY
import com.example.mastermind.constants.HOST_NAME_KEY
import com.example.mastermind.constants.USER_NAME_KEY
import com.example.mastermind.gamesList.presenter.GamesListPresenter
import com.example.mastermind.gamesList.presenter.GamesListPresenterImpl
import kotlinx.android.synthetic.main.activity_existing_games.*
import server.GamesByName

interface ExisitingGamesView{
    fun turnOnProgress()
    fun turnOffProgress()
    fun displayGames(gamesByName: GamesByName)
}

class ExistingGamesActivity : AppCompatActivity(), ExisitingGamesView {

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
        gamesListView.adapter = GamesListAdapter(gamesByName)
    }

    override fun turnOnProgress() {
        gamesListProgressBar.visibility = View.VISIBLE
    }

    override fun turnOffProgress() {
        gamesListProgressBar.visibility = View.INVISIBLE
    }

}
