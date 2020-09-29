package com.example.cookbook.presentation.mainflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.example.cookbook.domain.models.Recipe
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment() {

    private lateinit var recipe: Recipe

    companion object {
        fun newInstance(recipe: Recipe): RecipeFragment {
            val instance = RecipeFragment()
            instance.recipe = recipe
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headerTextView.text = recipe.header
        bodyTextView.text = recipe.body

    }

}