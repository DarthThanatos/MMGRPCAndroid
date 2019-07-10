package com.example.mastermind.game.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mastermind.R
import com.example.mastermind.constants.GAME_ID_KEY
import com.example.mastermind.constants.GAME_NAME_KEY
import com.example.mastermind.constants.HOST_NAME_KEY
import com.example.mastermind.constants.USER_NAME_KEY
import com.example.mastermind.game.presenter.GamePresenter
import com.example.mastermind.game.presenter.GamePresenterImpl
import kotlinx.android.synthetic.main.activity_game.*
import server.Color
import server.Player

interface GameView{
    fun displayJoinedPlayer(player: Player)
    fun informOpponentLeftGame(opponent: Player)
    fun showWaitingProgress()
    fun hideWaitingProgress()
    fun informOpponentJoinedGame(opponent: Player)
    fun displayGuesserBoard()
    fun displayVerifierBoard()
    fun getCombinationColorArr(): Array<Color>
}

class GameActivity : AppCompatActivity(), GameView {

    private var presenter: GamePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initPresenter()
        presenter?.joinGame()
    }

    private fun initPresenter(){
        val userName = intent.getStringExtra(USER_NAME_KEY)
        val serverAddress = intent.getStringExtra(HOST_NAME_KEY)
        val gameName = intent.getStringExtra(GAME_NAME_KEY)
        val gameId = intent.getStringExtra(GAME_ID_KEY)
        presenter = GamePresenterImpl(serverAddress, gameName, gameId, userName)
        presenter?.attachView(this)
    }

    override fun onDestroy() {
        presenter?.detachView()
        presenter = null
        super.onDestroy()
    }

    override fun displayJoinedPlayer(player: Player) {
        Toast.makeText(this, "You are: ${player.playerName} with id: ${player.playerId} at game: ${player.gameId} with role: ${player.role.name}", Toast.LENGTH_LONG).show()
    }

    override fun informOpponentLeftGame(opponent: Player) {
        this.runOnUiThread( { Toast.makeText(this, "Your opponent: ${opponent.playerName} has left the room", Toast.LENGTH_LONG).show()})
    }

    override fun informOpponentJoinedGame(opponent: Player) {
        Toast.makeText(this, "Your opponent: ${opponent.playerName} has joined the room", Toast.LENGTH_LONG).show()
    }

    override fun getCombinationColorArr()
        = arrayOf(Color.BLUE, Color.RED, Color.GREEN, Color.RED)

    override fun displayGuesserBoard() {
        guesserBoardView.visibility = View.VISIBLE
    }

    override fun displayVerifierBoard() {
        verifierBoardView.visibility = View.VISIBLE
    }

    override fun showWaitingProgress() {
        waiting_progress_bar.visibility = View.VISIBLE
    }

    override fun hideWaitingProgress() {
        waiting_progress_bar.visibility = View.INVISIBLE
    }
}
