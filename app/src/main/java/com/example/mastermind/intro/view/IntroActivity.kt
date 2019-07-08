package com.example.mastermind.intro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mastermind.GameActivity
import com.example.mastermind.R
import com.example.mastermind.constants.GAME_ID_KEY
import com.example.mastermind.constants.GAME_NAME_KEY
import com.example.mastermind.constants.HOST_NAME_KEY
import com.example.mastermind.constants.USER_NAME_KEY
import com.example.mastermind.gamesList.view.ExistingGamesActivity
import com.example.mastermind.protocol.Protocol
import kotlinx.android.synthetic.main.activity_intro.*
import server.GameDescription
import java.lang.IllegalStateException


class IntroActivity : AppCompatActivity() {

    private var protocol: Protocol? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    @Suppress("UNUSED_PARAMETER")
    fun startGame(view: View){
        val createGame = createGameSwitch.isChecked

        if(!createGame) {
            moveToActivity(Intent(this, ExistingGamesActivity::class.java))
        }
        else {
            val serverAddress = serverAddrInput.text.toString()
            val gameName = gameNameInput.text.toString()
            protocol = Protocol(host = serverAddress, port = 50051)
            protocol?.runInBackground(
                task = task(gameName),
                onResult = onResult()
            )
        }
    }

    private fun moveToActivity(intent: Intent){
        val userName = usernameInput.text.toString()
        val serverAddress = serverAddrInput.text.toString()
        val gameName = gameNameInput.text.toString()
        intent.putExtra(HOST_NAME_KEY, serverAddress)
        intent.putExtra(GAME_NAME_KEY, gameName)
        intent.putExtra(USER_NAME_KEY, userName)
        startActivity(intent)

    }

    private fun task(gameName: String) = {
            blockingStub: server.GreeterGrpc.GreeterBlockingStub,
            asynchStub: server.GreeterGrpc.GreeterStub ->
                protocol?.createGame(gameName, blockingStub) ?: throw IllegalStateException("Protocol not initialized")
        }

    private fun onResult(): (GameDescription) -> Unit{
        return {
                gameDescription: GameDescription ->
                    moveToGameActivity(gameDescription.gameId)
        }
    }

    private fun moveToGameActivity(gameId: String){
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GAME_ID_KEY, gameId)
        moveToActivity(intent)
    }

    override fun onDestroy() {
        protocol?.shutdown()
        super.onDestroy()
    }

}
