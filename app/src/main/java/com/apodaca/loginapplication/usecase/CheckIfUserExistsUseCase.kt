package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.model.db.dao.UserDao
import timber.log.Timber
import javax.inject.Inject

class CheckIfUserExistsUseCase @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(email: String): Boolean {
        Timber.d("invoke: $email")
        return userDao.isRowIsExist(email)
    }
}