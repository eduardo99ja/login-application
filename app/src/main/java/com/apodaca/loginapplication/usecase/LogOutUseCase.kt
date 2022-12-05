package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.utils.DATASTORE_LOGGED_IN_EMAIL_KEY
import com.apodaca.loginapplication.utils.DatastoreManager
import timber.log.Timber
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val datastoreManager: DatastoreManager
) {

    suspend operator fun invoke() {
        Timber.d("Doing")
        datastoreManager.removeFromDatastore(DATASTORE_LOGGED_IN_EMAIL_KEY)
    }
}