package com.apodaca.loginapplication.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apodaca.loginapplication.model.db.dto.UserDto
import com.apodaca.loginapplication.model.db.dao.UserDao

@Database(entities = [UserDto::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
