package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.model.db.dao.UserDao
import com.apodaca.loginapplication.model.db.dto.UserDto
import javax.inject.Inject

class AddUserToDatabaseUseCase @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(userDto: UserDto) {
        userDao.insert(userDto)
    }

}