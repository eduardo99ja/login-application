package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.model.domain.User
import com.apodaca.loginapplication.model.db.dao.UserDao
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(email: String, password: String): Result {
        return try {
            val userDto = userDao.findByEmail(email)
            if (userDto.password != password) {
                throw IllegalArgumentException("LoginUserUseCase: failed, passwords do not match")
            }
            Result.Success(User(userDto.email))
        } catch (e: IllegalArgumentException) {
            Result.Failure(FailureReason.INVALID_PASSWORD)
        } catch (e: Exception) {
            Result.Failure(FailureReason.USER_NOT_FOUND)
        }

    }

    sealed class Result {
        data class Success(val user: User) : Result()
        data class Failure(val failureResult: FailureReason) : Result()
    }

    enum class FailureReason {
        USER_NOT_FOUND,
        INVALID_PASSWORD
    }
}