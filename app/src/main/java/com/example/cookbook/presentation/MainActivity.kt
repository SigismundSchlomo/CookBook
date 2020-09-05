package com.example.cookbook.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.cookbook.App
import com.example.cookbook.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Main activity onCreate")

        supportFragmentManager.commit {
            replace(R.id.main_fragment_container, ListFragment.newInstance())
        }

    }
}