package com.example.cookbook.presentation.mainflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookbook.R
import com.example.cookbook.di.mainflowcomponent.DaggerMainFlowComponent
import com.example.cookbook.di.mainflowcomponent.MainFlowComponent
import com.example.cookbook.presentation.authflow.AuthActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val pagerAdapter = MainTabsAdapter(this, 2)

    val mainFlowComponent: MainFlowComponent by lazy {
        DaggerMainFlowComponent.factory().create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Main activity onCreate")

        pager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.recipes_tab_label)
                }
                1 -> {
                    tab.text = getString(R.string.shoppingList_tab_label)
                }
            }

        }.attach()

    }

    fun moveToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}