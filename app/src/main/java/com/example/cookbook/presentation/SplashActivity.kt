package com.example.cookbook.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.App
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import com.example.cookbook.presentation.authflow.AuthActivity
import com.example.cookbook.presentation.mainflow.MainActivity
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        setContentView(R.layout.activity_splash)

    }

    override fun onStart() {
        super.onStart()
        Timber.d("Splash activity onStart")
        if (viewModel.isUserLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}