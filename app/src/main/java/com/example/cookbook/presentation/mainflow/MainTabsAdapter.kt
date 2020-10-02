package com.example.cookbook.presentation.mainflow

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cookbook.presentation.mainflow.recipeslist.RecipeListFragment
import com.example.cookbook.presentation.mainflow.shopping.ShoppingListFragment

class MainTabsAdapter(activity: MainActivity, private val itemCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            RecipeListFragment.newInstance()
        } else {
            ShoppingListFragment.newInstance()
        }
    }


}