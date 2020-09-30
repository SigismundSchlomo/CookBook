package com.example.cookbook.presentation.mainflow

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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

    private fun addItemToList(layout: LinearLayout, message: String) {
        val textView = TextView(requireContext())
        textView.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            text = message
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            setTextColor(Color.BLACK)
        }
        layout.addView(textView)
    }

}