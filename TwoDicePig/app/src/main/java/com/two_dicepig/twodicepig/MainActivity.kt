package com.two_dicepig.twodicepig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import com.two_dicepig.twodicepig.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var player : Int = 1
    var score : Int = 0
    var p1 = Player()
    var p2 = Player()
    var die1 = 1
    var die2 = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageChange(imageExtract(die1), imageExtract(die2))

        p1.setPlayerName("Player 1")
        p2.setPlayerName("Player 2")
        p1.snakeEyes()
        p2.snakeEyes()
        player1.setText(p1.getScore().toString())
        player2.setText(p2.getScore().toString())
        currentPlayer.setText(p1.getPlayerName())
        newGame.isVisible = false
        newGame.isEnabled = false
        hold.isEnabled = false


    }

    fun rolled (view: View) {
        hold.isEnabled = true
        var dieImage1 = ""
        var dieImage2 = ""
        die1 = Random.nextInt(1..6)
        die2 = Random.nextInt(1..6)

        dieImage1 = imageExtract(die1)
        dieImage2 = imageExtract(die2)

        imageChange(dieImage1, dieImage2)
        if ((die1 == 1 && die2 == 1) || (die1 == 1 || die2 == 1)) {
            if ((die1 == 1 && die2 == 1)) {
                score = 0
                snakeEyed(player, p1, p2)
                held(view)

            }
            else {
                score = 0
                held(view)
            }
        }
        else {
            score += (die1 + die2)
            total.setText(score.toString())
        }
        if (die1 == die2) {
            hold.isEnabled = false
            }


    }

    fun imageChange (img1 : String, img2 : String) {

                var die1 = getResources().getIdentifier(img1 , "drawable", getPackageName())
                var die2 = getResources().getIdentifier(img2 , "drawable", getPackageName())
                image1.setImageResource(die1)
                image2.setImageResource(die2)
                image1.maxHeight = 300
                image2.maxHeight = 300

    }

    fun playerChange(player: Int, p1 : Player, p2 : Player) {
        if (player == 1) {
            this.player = 2
            currentPlayer.setText(p2.getPlayerName())
        }
        else {
            this.player = 1
            currentPlayer.setText(p1.getPlayerName())
        }
    }

    fun held(view: View) {
        if (player == 1) {
            p1.setScore(score)
            player1.setText(p1.getScore().toString())
            playerChange(player, p1, p2)
            score = 0
            total.setText(score.toString())
            hold.isEnabled = false
        }
        else {
            p2.setScore(score)
            player2.setText(p2.getScore().toString())
            playerChange(player, p1, p2)
            score = 0
            total.setText(score.toString())
            hold.isEnabled = false
        }
        if (p1.getScore() >= 50 || p2.getScore() >= 50) {
            roll.isEnabled = false
            hold.isEnabled = false
            newGame.isVisible = true
            newGame.isEnabled = true
            if (p1.getScore() >= 50){
                player1.setText("Player 1 Wins")
            }
            else {
                player2.setText("Player 2 wins")
            }
        }
    }

    fun imageExtract (die1: Int) : String {

        if (die1 == 1)
            return "one"
        if (die1 == 2)
            return "two"
        if (die1 == 3)
            return "three"
        if (die1 == 4)
            return "four"
        if (die1 == 5)
            return "five"
        if (die1 == 6)
            return "six"

//        if (die2 == 1)
//            return "one"
//        if (die2 == 2)
//            return "two"
//        if (die2 == 3)
//            return "three"
//        if (die2 == 4)
//            return "four"
//        if (die2 == 5)
//            return "five"
//        if (die2 == 6)
//            return "six"
        return " "
    }

    fun snakeEyed (player: Int, p1: Player, p2: Player) {
        if (player == 1) {
            p1.snakeEyes()
        }
        else {
            p2.snakeEyes()
        }
    }

    fun newGame (view : View) {
        player = 1
        score = 0
        p1.snakeEyes()
        p2.snakeEyes()
        player1.setText(score.toString())
        player2.setText(score.toString())
        currentPlayer.setText(p1.getPlayerName())
        roll.isEnabled = true
        hold.isEnabled = false
        newGame.isEnabled = false
        newGame.isVisible = false
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("p1s", p1.getScore())
        outState.putInt("p2s", p2.getScore())
        outState.putInt("score", score)
        outState.putInt("turn", player)
        outState.putInt("die1", die1)
        outState.putInt("die2", die2)
        outState.putBoolean("hold status", hold.isEnabled)
        outState.putBoolean("roll status", roll.isEnabled)
        outState.putBoolean("newgame status", newGame.isEnabled)
        outState.putBoolean("newgame visible", newGame.isVisible)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        p1.changeScore(savedInstanceState.getInt("p1s"))
        p2.changeScore(savedInstanceState.getInt("p2s"))
        score = savedInstanceState.getInt("score")
        player1.setText(p1.getScore().toString())
        player2.setText(p2.getScore().toString())
        total.setText(score.toString())
        player = savedInstanceState.getInt("turn")
        if (player == 1) {
            currentPlayer.setText(p1.getPlayerName())
        }
        else {
            currentPlayer.setText(p2.getPlayerName())
        }
        imageChange(imageExtract(savedInstanceState.getInt("die1")), imageExtract(savedInstanceState.getInt("die2")))
        hold.isEnabled = savedInstanceState.getBoolean("hold status")
        roll.isEnabled = savedInstanceState.getBoolean("roll status")
        newGame.isEnabled = savedInstanceState.getBoolean("newgame status")
        newGame.isVisible = savedInstanceState.getBoolean("newgame visible")
    }


}