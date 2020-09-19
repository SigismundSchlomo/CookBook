package com.example.cookbook.presentation.authflow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.cookbook.App
import com.example.cookbook.R
import com.example.cookbook.di.injectViewModel
import com.example.cookbook.domain.models.isValid
import com.example.cookbook.presentation.ErrorMessage
import com.example.cookbook.presentation.mainflow.MainActivity
import com.example.cookbook.utils.ConnectivityManagerWrapper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

import javax.inject.Inject

class RegisterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var connectivityManagerWrapper: ConnectivityManagerWrapper

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val displayName = createNameEditText.text.toString()
            viewModel.createAccount(email, password, displayName)
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            val currentDate = Calendar.getInstance().time
            if (user.token.isValid(currentDate)) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        //TODO: Make own error handling tool for every viewModel
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            val messageResource = when (errorMessage) {
                ErrorMessage.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                ErrorMessage.UNKNOWN_ERROR -> R.string.unknown_error
                ErrorMessage.DATA_FROM_DATABASE -> R.string.data_from_database
            }
            showErrorMessage(messageResource)
        }

    }

    private fun showErrorMessage(stringResource: Int) {
        Snackbar.make(requireView(), stringResource, Snackbar.LENGTH_SHORT).show()
    }

}