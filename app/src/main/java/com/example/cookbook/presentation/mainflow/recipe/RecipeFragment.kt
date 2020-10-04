package com.example.cookbook.presentation.mainflow.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.R
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.presentation.mainflow.MainActivity
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

        topAppBar.apply {
            setNavigationIcon(R.drawable.ic_back_white_24)
            setNavigationOnClickListener {
                navigateBack()
            }

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        true
                    }
                    else -> false
                }
            }
        }

        val ingredientAdapter = IngredientAdapter()
        ingredientAdapter.ingredients = recipe.ingredients.toMutableList()
        ingredientContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientAdapter
        }

        val cookingAdapter = CookingAdapter()
        cookingAdapter.cookingSteps = recipe.cookingSteps.toMutableList()
        cookingStepsContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cookingAdapter
        }

        ingredientListLabel.setEndIconOnClickListener {
            if (ingredientContainer.visibility == View.GONE) {
                showIngredientList()
            } else {
                hideIngredientList()
            }
        }

        cookingStepsListLabel.setEndIconOnClickListener {
            if (cookingStepsContainer.visibility == View.GONE) {
                showCookingStepsList()
            } else {
                hideCookingStepsList()
            }
        }

    }

    private fun navigateBack() {
        (activity as MainActivity).onBackPressed()
    }

    private fun showIngredientList() {
        ingredientContainer.visibility = View.VISIBLE
        ingredientListLabel.endIconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up_24, null)
    }

    private fun hideIngredientList() {
        ingredientContainer.visibility = View.GONE
        ingredientListLabel.endIconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down_24, null)
    }

    private fun showCookingStepsList() {
        cookingStepsContainer.visibility = View.VISIBLE
        cookingStepsListLabel.endIconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up_24, null)
    }

    private fun hideCookingStepsList() {
        cookingStepsContainer.visibility = View.GONE
        cookingStepsListLabel.endIconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down_24, null)
    }

}