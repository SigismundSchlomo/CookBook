package com.example.cookbook.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.App
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import kotlinx.android.synthetic.main.fragment_recipes_list.*
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: RecipeViewModel

    private val recipeAdapter =
        RecipeListAdapter()

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshRecipes()

        recipeListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        viewModel.recipesLiveData.observe(viewLifecycleOwner) {
            recipeAdapter.items = it
        }

        addRecipeButton.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                addToBackStack(null)
                replace(R.id.main_fragment_container, CreateFragment.newInstance())
            }
        }

    }
}