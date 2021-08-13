package com.backendpapa.clockfighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
//Initialize the varibles to connect to our widgets
    private lateinit var countDownTimer: CountDownTimer

    private lateinit var gameScore:TextView
    private lateinit var gameTime:TextView
    private lateinit var gameButton:Button
    private var started=false
//    state
    private var score=0
    private var time=60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScore=findViewById(R.id.game_score)
        gameTime=findViewById(R.id.game_time)
        gameButton=findViewById(R.id.play_button)
        resetGame()
        gameButton.setOnClickListener{
//            gets called when button is clicked
            if(started==false){
                start()
            }
            incrementScore()
            gameScore.text=getString(R.string.game_score,score)
        }
    }
    fun start(){
//        Timer
        countDownTimer.start()
        started=true
    }
    fun resetGame(){
//        reset our game
        gameScore.text=getString(R.string.game_score,score)
        gameTime.text=getString(R.string.game_time,time)

        countDownTimer=object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
//
                time=millisUntilFinished.toInt()/1000
                gameTime.text=getString(R.string.game_time,time)
            }

            override fun onFinish() {
//
                print("not yet")
                endGame()
            }

        }
        started=false
    }
    fun endGame(){
//        when the game stops
        Toast.makeText(this,getString(R.string.final_msg,score),Toast.LENGTH_LONG).show()
        score=0
        time=60
        resetGame()
    }
    fun incrementScore(){
        score++
    }

}