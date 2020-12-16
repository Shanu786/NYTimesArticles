package com.shanu.nytimesarticle.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shanu.nytimesarticle.R

class SplashScreen : AppCompatActivity() {

    private var mHandler: Handler? = null
    private val SPLASH_SCREEN_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mHandler = Handler()
        mHandler?.postDelayed(mRunnable, SPLASH_SCREEN_TIME_OUT)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private var mRunnable : Runnable = Runnable {
        if (!isFinishing) {
            val startMainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(startMainActivity)
            finish()
        }
    }
}