package com.apodaca.loginapplication.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apodaca.loginapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
}
