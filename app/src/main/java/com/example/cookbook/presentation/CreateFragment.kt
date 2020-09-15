package com.example.cookbook.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import kotlinx.android.synthetic.main.fragment_create_recipe.*
import javax.inject.Inject

class CreateFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: RecipeViewModel

    companion object {
        fun newInstance(): CreateFragment {
            return CreateFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).recipesComponent.inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton.setOnClickListener {
            createRecipe()
        }
    }

    private fun createRecipe() {
        val header = headerEditText.text.toString()
        val body = bodyEditText.text.toString()
        viewModel.createRecipe(header, body)
    }

}