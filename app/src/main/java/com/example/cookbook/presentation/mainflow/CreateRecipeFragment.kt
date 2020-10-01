package com.example.cookbook.presentation.mainflow

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_create_recipe.*
import javax.inject.Inject

class CreateRecipeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: RecipeViewModel //TODO: Create own viewModel to the class

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

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)

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

    }

    private fun createRecipe() { //TODO: rewrite to create recipe with ingredients and cooking steps
        val header = headerEditText.text.toString()
        val body = bodyEditText.text.toString()
        viewModel.createRecipe(header, body)
    }

    private fun navigateBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
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
                //TODO: Add method to viewModel to create ingredient and store it
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
                //TODO: Create method int viewModel to create step and save it
            }
            setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

}