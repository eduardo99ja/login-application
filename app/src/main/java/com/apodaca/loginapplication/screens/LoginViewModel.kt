package com.apodaca.loginapplication.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apodaca.loginapplication.usecase.LoginUserUseCase
import com.apodaca.loginapplication.utils.isValidEmail
import com.apodaca.loginapplication.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _error = MutableSharedFlow<Unit>()
    val error: Flow<Unit> = _error
    private val _navigateToApp = MutableSharedFlow<Unit>()
    val navigateToApp: Flow<Unit> = _navigateToApp
    private val _state = MutableStateFlow(LoginState())
    val state: Flow<LoginState> = _state


    fun loginClicked(email: String, password: String) {
        Timber.d("loginClicked: $email, $password")


        if (validateInputs(email, password)) {
            viewModelScope.launch {
                val result = loginUserUseCase(email, password)
                when (result) {
                    LoginUserUseCase.Result.Failure -> {
                        _error.emit(Unit)
                    }
                    LoginUserUseCase.Result.Success -> _navigateToApp.emit(Unit)
                }
            }
        }
    }

    fun signUpClicked(email: String, password: String) {


    }

    fun forgotPasswordClicked() {

    }


    private fun validateInputs(email: String, password: String): Boolean {
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword()

        _state.value = _state.value.copy(
            isEmailValid = isEmailValid,
            isPasswordValid = isPasswordValid
        )

        return isEmailValid && isPasswordValid
    }


}