package com.apodaca.loginapplication.global

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apodaca.loginapplication.usecase.ObserveLoggedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthenticationviewModel @Inject constructor(
    private val observeLoggedInUserUseCase: ObserveLoggedInUserUseCase
) : ViewModel() {
    private val _logout = MutableSharedFlow<Unit>()
    val logout: Flow<Unit> = _logout

    init {
        viewModelScope.launch {
            observeLoggedInUserUseCase().onEach { user ->
                Timber.d(user.toString())
                if (user == null) {
                    _logout.emit(Unit)
                }
            }.launchIn(this)
        }
    }

}