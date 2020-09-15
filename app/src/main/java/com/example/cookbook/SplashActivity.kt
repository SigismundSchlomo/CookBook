package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookbook.auth.Auth
import com.example.cookbook.presentation.MainActivity
import com.example.cookbook.presentation.authflow.AuthActivity
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var auth: Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timber.d("Splash activity onCreate")

    }

    override fun onStart() {
        super.onStart()
        Timber.d("Splash activity onStart")
        if (auth.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}