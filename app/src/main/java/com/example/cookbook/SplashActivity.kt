package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookbook.presentation.MainActivity
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timber.d("Splash activity onCreate")

    }

    override fun onStart() {
        super.onStart()
        Timber.d("Splash activity onStart")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}