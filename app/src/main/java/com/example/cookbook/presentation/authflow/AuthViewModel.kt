package com.example.cookbook.presentation.authflow

import android.accounts.NetworkErrorException
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.BuildConfig
import com.example.cookbook.domain.models.User
import com.example.cookbook.domain.usecases.AuthInteractor
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() = _userLiveData

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    var savedEmail: String = ""

    fun login(email: String, password: String) {

        if (BuildConfig.DEBUG) {
            Timber.d(email)
            Timber.d(password)
        }

        if (isEmailInvalid(email)) {
            _errorMessage.value = ErrorMessage.EMAIL_INVALID
        } else if (isPasswordInvalid(password)) {
            _errorMessage.value = ErrorMessage.PASSWORD_INVALID
        }

        viewModelScope.launch {
            try {
                _userLiveData.value = authInteractor.loginUser(email, password)
            } catch (ne: NetworkErrorException) {
                Timber.d(ne)
                _errorMessage.value = when (ne.message) {
                    "HTTP 503 Service Unavailable" -> ErrorMessage.SERVICE_UNAVAILABLE
                    else -> ErrorMessage.UNKNOWN_ERROR
                }
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
                _userLiveData.value = authInteractor.createUser(email, password, name)
            } catch (ne: NetworkErrorException) {
                Timber.d(ne)
                _errorMessage.value = when (ne.message) {
                    "HTTP 503 Service Unavailable" -> ErrorMessage.SERVICE_UNAVAILABLE
                    else -> ErrorMessage.UNKNOWN_ERROR
                }
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNKNOWN_ERROR
            }
        }
    }

    private fun isEmailInvalid(email: String): Boolean {
        return email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordInvalid(password: String): Boolean {
        return password.isEmpty() && password.length < 8
    }

    enum class ErrorMessage {
        UNKNOWN_ERROR,
        SERVICE_UNAVAILABLE,
        EMAIL_INVALID,
        PASSWORD_INVALID
    }

}