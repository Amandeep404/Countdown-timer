package com.example.countdownapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var countdown_timer: CountDownTimer? = null
    private var time_in_milliseconds = 60000L
    private var pauseOffSet = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_timer.text= "${(time_in_milliseconds/1000).toString()}"

        startBtn.setOnClickListener{
                starTimer(pauseOffSet)
        }

        pauseBtn.setOnClickListener{
            pauseTimer()
        }

        resetBtn.setOnClickListener{
                resetTimer()
        }
    }
    private fun starTimer(pauseOffSetL : Long){
        countdown_timer = object : CountDownTimer(time_in_milliseconds - pauseOffSetL, 1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = time_in_milliseconds - millisUntilFinished
                tv_timer.text= (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer finished", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun pauseTimer(){
        if (countdown_timer!= null){
            countdown_timer!!.cancel()
        }
    }

    private fun resetTimer(){
        if (countdown_timer!= null){
            countdown_timer!!.cancel()
            tv_timer.text = " ${(time_in_milliseconds/1000).toString()}"
            countdown_timer = null
            pauseOffSet =0
        }
    }
}