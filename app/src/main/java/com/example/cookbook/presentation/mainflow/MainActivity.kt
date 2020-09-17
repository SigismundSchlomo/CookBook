package com.example.cookbook.presentation.mainflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.cookbook.R
import com.example.cookbook.di.mainflowcomponent.DaggerMainFlowComponent
import com.example.cookbook.di.mainflowcomponent.MainFlowComponent
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val mainFlowComponent: MainFlowComponent by lazy {
        DaggerMainFlowComponent.factory().create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Main activity onCreate")

        supportFragmentManager.commit {
            replace(
                R.id.main_fragment_container,
                ListFragment.newInstance()
            )
        }

    }
}