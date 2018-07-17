package com.example.administrator.serverapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 4000 //Splash screen duration of 4 seconds
    private var firstTime: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Hides toolbar/action bar on splash screen
        supportActionBar?.hide()

        //Hides navigation bar and status bar on splash screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        firstTime = sharedPreferences.getBoolean("firstTime", true)

        //Runs the splash screen one time on launch instead of every time on launch
        if (firstTime) {
            Handler().postDelayed({
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                firstTime = false
                editor.putBoolean("firstTime", firstTime)
                editor.apply()

                val home = Intent(this@SplashScreen, HomeScreen::class.java)
                startActivity(home)
                finish()
            }, SPLASH_TIME_OUT.toLong())
        } else {
            Handler().postDelayed({
                val home = Intent(this@SplashScreen, HomeScreen::class.java)
                startActivity(home)
                finish()
            }, SPLASH_TIME_OUT.toLong())
        }
    }

    //Splash screen is fullscreen, with no navigation bar/status bar,
    // and you can't press the screen to access the navigation bar
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus) {
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}