package com.example.cookbook.presentation.mainflow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.R
import com.example.cookbook.di.mainflowcomponent.DaggerMainFlowComponent
import com.example.cookbook.di.mainflowcomponent.MainFlowComponent
import com.example.cookbook.presentation.authflow.AuthActivity
import com.example.cookbook.presentation.mainflow.recipe.CreateRecipeFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    val mainFlowComponent: MainFlowComponent by lazy {
        DaggerMainFlowComponent.factory().create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Main activity onCreate")

        pager.adapter = MainTabsAdapter(this, 2)
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

        mainAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    viewModel.logout()
                    moveToAuthActivity()
                    true
                }
                else -> false
            }
        }

        addRecipeButton.setOnClickListener {
            moveToCreateFragment()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideFragmentContainer()
    }

    fun showFragmentContainer() {
        tabLayoutContainer.visibility = View.GONE
        addRecipeButton.visibility = View.GONE
        mainAppBar.visibility = View.GONE
        mainFragmentContainer.visibility = View.VISIBLE
    }

    fun hideFragmentContainer() {
        tabLayoutContainer.visibility = View.VISIBLE
        addRecipeButton.visibility = View.VISIBLE
        mainAppBar.visibility = View.VISIBLE
        mainFragmentContainer.visibility = View.GONE
    }

    private fun moveToCreateFragment() {
        showFragmentContainer()
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.mainFragmentContainer, CreateRecipeFragment.newInstance())
        }
    }

    private fun moveToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}