package com.apodaca.loginapplication.usecase

import com.apodaca.loginapplication.utils.DATASTORE_LOGGED_IN_EMAIL_KEY
import com.apodaca.loginapplication.utils.DatastoreManager
import timber.log.Timber
import javax.inject.Inject

open class AddLoggedInEmailToDataStoreUseCase @Inject constructor(
    private val datastoreManager: DatastoreManager
) {

    open suspend operator fun invoke(email: String) {
        Timber.d("invoke: $email")
        datastoreManager.addToDatastore(DATASTORE_LOGGED_IN_EMAIL_KEY, email)
    }
}