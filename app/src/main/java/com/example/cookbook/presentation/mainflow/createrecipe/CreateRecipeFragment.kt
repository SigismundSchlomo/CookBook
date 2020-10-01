package com.example.cookbook.presentation.mainflow.createrecipe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import com.example.cookbook.domain.models.CookingStep
import com.example.cookbook.domain.models.Ingredient
import com.example.cookbook.presentation.mainflow.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_create_recipe.*
import javax.inject.Inject

class CreateRecipeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CreateRecipeViewModel

    companion object {
        fun newInstance(): CreateRecipeFragment {
            return CreateRecipeFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainFlowComponent.inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

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

        topAppBar.apply {
            setNavigationIcon(R.drawable.ic_back_white_24)
            setNavigationOnClickListener {
                navigateBack()
            }

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.done -> {
                        createRecipe()
                        navigateBack()
                        true
                    }
                    else -> false
                }
            }
        }

        ingredientListLabel.setEndIconOnClickListener {
            pickIngredient()
        }

        cookingStepsListLabel.setEndIconOnClickListener {
            pickStep()
        }

        viewModel.ingredients.observe(viewLifecycleOwner,
            Observer<MutableList<Ingredient>> { list ->
                list.forEach { ingredient ->
                    //TODO: add item to list
                }
            }
        )

        viewModel.cookingSteps.observe(viewLifecycleOwner,
            Observer<MutableList<CookingStep>> { list ->
                list.forEach { step ->
                    //TODO: add item to list
                }
            }
        )

    }

    private fun createRecipe() {
        val header = headerEditText.text.toString()
        val body = bodyEditText.text.toString()
        viewModel.createRecipe(header, body)
    }

    private fun navigateBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    @SuppressLint("InflateParams")
    private fun pickIngredient() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_ingredient, null)
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle("New Ingredient")
            setView(dialogView)
            setPositiveButton(android.R.string.ok) { _, _ ->
                val nameInput =
                    dialogView.findViewById<TextInputEditText>(R.id.ingredientNameEditText)
                val name = nameInput.text.toString()
                val amountInput =
                    dialogView.findViewById<TextInputEditText>(R.id.ingredientAmountEditText)
                val amount = amountInput.text.toString()
                viewModel.createIngredient(name, amount)
            }
            setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

    @SuppressLint("InflateParams")
    private fun pickStep() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_step, null)
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle("New Step")
            setView(dialogView)
            setPositiveButton(android.R.string.ok) { _, _ ->
                val descriptionInput =
                    dialogView.findViewById<TextInputEditText>(R.id.descriptionEditText)
                val description = descriptionInput.text.toString()
                viewModel.createCookingStep(description)
            }
            setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

}