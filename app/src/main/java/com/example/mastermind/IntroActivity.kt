package com.example.mastermind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mastermind.constants.GAME_NAME_KEY
import com.example.mastermind.constants.HOST_NAME_KEY
import com.example.mastermind.constants.USER_NAME_KEY
import com.example.mastermind.gamesList.view.ExistingGamesActivity
import com.example.mastermind.protocol.Protocol
import kotlinx.android.synthetic.main.activity_intro.*
import java.util.logging.Level
import java.util.logging.Logger

class IntroActivity : AppCompatActivity() {

    val logger = Logger.getLogger(IntroActivity::class.simpleName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    @Suppress("UNUSED_PARAMETER")
    fun startGame(view: View){
        val createGame = createGameSwitch.isChecked
        val userName = usernameInput.text.toString()
        val serverAddress = serverAddrInput.text.toString()
        val gameName = gameNameInput.text.toString()
        logger.log(Level.INFO, "create game: $createGame, user name: $userName, server address: $serverAddress, game name: $gameName")
        if(!createGame){
            val intent = Intent(this, ExistingGamesActivity::class.java)
            intent.putExtra(HOST_NAME_KEY, serverAddress)
            intent.putExtra(GAME_NAME_KEY, gameName)
            intent.putExtra(USER_NAME_KEY, userName)
            startActivity(intent)
        }
        else{
            val protocol = Protocol(host=serverAddress, port = 50051)
            protocol.runInBackground(
                task={blockingStub: server.GreeterGrpc.GreeterBlockingStub, asynchStub: server.GreeterGrpc.GreeterStub -> protocol.createGame(gameName, blockingStub)},
                onResult ={ Toast.makeText(this, "created game with id: ${it.gameId}", Toast.LENGTH_LONG).show()}
            )
        }
    }

}
