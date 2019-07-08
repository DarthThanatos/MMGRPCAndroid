package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mastermind.constants.GAME_ID_KEY
import com.example.mastermind.constants.GAME_NAME_KEY
import com.example.mastermind.constants.HOST_NAME_KEY
import com.example.mastermind.constants.USER_NAME_KEY

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val userName = intent.getStringExtra(USER_NAME_KEY)
        val serverAddress = intent.getStringExtra(HOST_NAME_KEY)
        val gameName = intent.getStringExtra(GAME_NAME_KEY)
        val gameId = intent.getStringExtra(GAME_ID_KEY)
        Toast.makeText(this, "user name: $userName, server address: $serverAddress, game name: $gameName, game id: $gameId", Toast.LENGTH_LONG).show()
    }
}
