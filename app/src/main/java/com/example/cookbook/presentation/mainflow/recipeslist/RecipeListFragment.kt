package com.example.cookbook.presentation.mainflow.recipeslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.presentation.ErrorMessage
import com.example.cookbook.presentation.mainflow.MainActivity
import com.example.cookbook.presentation.mainflow.recipe.RecipeFragment
import com.example.cookbook.utils.ConnectivityManagerWrapper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_recipes_list.*
import javax.inject.Inject

class RecipeListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listViewModel: RecipeListViewModel

    @Inject
    lateinit var connectivityManagerWrapper: ConnectivityManagerWrapper

    private val recipeAdapter = RecipeListAdapter()

    companion object {
        fun newInstance(): RecipeListFragment {
            return RecipeListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainFlowComponent.inject(this)
        listViewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        listViewModel.loadRecipes()

        recipeAdapter.listener = { recipe ->
            (activity as MainActivity).hideFragmentContainer()
            navigateToRecipeFragment(recipe)
        }

        recipeListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(this::deleteRecipe))
        itemTouchHelper.attachToRecyclerView(recipeListView)

        refreshLayout.setOnRefreshListener {
            listViewModel.refreshRecipes()
        }

        listViewModel.recipesLiveData.observe(viewLifecycleOwner,
            Observer<List<Recipe>> { list ->
                recipeAdapter.items = (list as MutableList<Recipe>)
                refreshLayout.isRefreshing = false
            })

        listViewModel.errorMessage.observe(viewLifecycleOwner,
            Observer<ErrorMessage> { errorMessage ->
                val messageResource = when (errorMessage) {
                    ErrorMessage.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                    ErrorMessage.UNKNOWN_ERROR -> R.string.unknown_error
                    ErrorMessage.DATA_FROM_DATABASE -> R.string.data_from_database
                    else -> R.string.unknown_error
                }
                showErrorMessage(messageResource)
                refreshLayout.isRefreshing = false
            })
    }

    private fun showErrorMessage(stringResource: Int) {
        Snackbar.make(requireView(), stringResource, Snackbar.LENGTH_SHORT).show()
    }

    private fun deleteRecipe(position: Int) {
        val recipe = recipeAdapter.items[position]
        listViewModel.deleteRecipe(recipe)
        recipeAdapter.removeAt(position)
    }

    private fun navigateToRecipeFragment(recipe: Recipe) {
        activity?.supportFragmentManager?.commit {
            addToBackStack(null)
            replace(R.id.mainFragmentContainer, RecipeFragment.newInstance(recipe))
        }
    }

}