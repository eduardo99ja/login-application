package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.model.domain.User
import com.apodaca.loginapplication.utils.DATASTORE_LOGGED_IN_EMAIL_KEY
import com.apodaca.loginapplication.utils.DatastoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveLoggedInUserUseCase @Inject constructor(
    private val datastoreManager: DatastoreManager
) {

    operator fun invoke(): Flow<User?> {
        return datastoreManager.observeKeyValue(DATASTORE_LOGGED_IN_EMAIL_KEY).map {
            if (it != null) {
                User(it)
            } else {
                null
            }
        }
    }
}