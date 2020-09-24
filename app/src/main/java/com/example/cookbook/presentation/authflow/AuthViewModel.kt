package com.example.cookbook.presentation.authflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.BuildConfig
import com.example.cookbook.domain.models.User
import com.example.cookbook.domain.usecases.UseCases
import com.example.cookbook.presentation.ErrorMessage
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> //TODO: Improve Error handling (make it unique to the network service)
        get() = _userLiveData

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun login(email: String, password: String) {
        if (BuildConfig.DEBUG) {
            Timber.d(email)
            Timber.d(password)
        }
        viewModelScope.launch {
            try {
                _userLiveData.value = useCases.loginUser(email, password)
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNKNOWN_ERROR
            }
        }
    }

    fun createAccount(email: String, password: String, name: String) {
        if (BuildConfig.DEBUG) {
            Timber.d(email)
            Timber.d(password)
            Timber.d(name)
        }
        viewModelScope.launch {
            try {
                _userLiveData.value = useCases.createUser(email, password, name)
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNKNOWN_ERROR
            }
        }
    }

}