package com.example.schoolsguide

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setBackground()

    }

    fun setBackground() {
        handler = Handler()
        handler.postDelayed({
            if (onBoardingIsFinished()){
                val inte = Intent(this,SignInActivity::class.java)
                startActivity(inte)
            }else{
                val inte = Intent(this,OnBoardingActivity::class.java)
                startActivity(inte)
            }
            finish()
        }, 4000)


    }

    private fun onBoardingIsFinished(): Boolean{
        val sharedPreferences = getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished", false)
    }
}