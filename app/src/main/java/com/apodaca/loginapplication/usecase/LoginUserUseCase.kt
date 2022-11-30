package com.apodaca.loginapplication.usecase

import timber.log.Timber
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val addLoggedInEmailToDataStoreUseCase: AddLoggedInEmailToDataStoreUseCase
) {

    suspend operator fun invoke(email: String, password: String) {
        Timber.d("invoke: $email")
        try {
            val userDto = getUserByEmailUseCase(email)
            if (userDto.password != password) {
                Timber.e("LoginUserUseCase: failed, passwords do not match")
            }
            addLoggedInEmailToDataStoreUseCase(email)
        } catch (e: Exception) {
            Timber.e("LoginUserUseCase: failed, exception: ${e.message}")
        }

    }


}