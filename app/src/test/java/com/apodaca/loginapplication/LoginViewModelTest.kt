package com.apodaca.loginapplication

import app.cash.turbine.test
import com.apodaca.loginapplication.screens.login.LoginState
import com.apodaca.loginapplication.screens.login.LoginViewModel
import com.apodaca.loginapplication.usecase.GetForgottenPasswordUseCase
import com.apodaca.loginapplication.usecase.LoginUserUseCase
import com.apodaca.loginapplication.usecase.RegisterUserUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var loginUserUseCase: LoginUserUseCase
    private lateinit var registerUserUseCase: RegisterUserUseCase
    private lateinit var getForgottenPasswordUseCase: GetForgottenPasswordUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        loginUserUseCase = mock(LoginUserUseCase::class.java)
        registerUserUseCase = mock(RegisterUserUseCase::class.java)
        getForgottenPasswordUseCase = mock(GetForgottenPasswordUseCase::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun loginClicked_validateInputsErrors() {
        val viewModel = LoginViewModel(
            loginUserUseCase,
            registerUserUseCase,
            getForgottenPasswordUseCase
        )

        val invalidUserName = ""
        val invalidPassword = ""
        viewModel.loginClicked(invalidUserName, invalidPassword)

        runTest {
            viewModel.state.test {
                assertEquals(LoginState(false,false), awaitItem())
            }
        }
    }
}