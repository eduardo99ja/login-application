package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.model.db.dao.UserDao
import com.apodaca.loginapplication.model.db.dto.UserDto
import timber.log.Timber
import javax.inject.Inject

open class GetUserByEmailUseCase @Inject constructor(
    private val userDao: UserDao
) {

    open suspend operator fun invoke(email: String): UserDto {
        Timber.d("invoke: $email")
        return userDao.findByEmail(email)
    }
}
