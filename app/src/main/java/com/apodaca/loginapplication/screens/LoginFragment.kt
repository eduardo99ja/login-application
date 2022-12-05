package com.apodaca.loginapplication.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.apodaca.loginapplication.R
import com.apodaca.loginapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            viewModel.loginClicked(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        with(lifecycleScope) {
            viewModel.state.onEach { state ->
                Timber.d(state.toString())
                binding.usernameLayout.error = if (state.isEmailValid) {
                    null
                } else {
                    getString(R.string.login_invalid_email)
                }
                binding.passwordLayout.error = if (state.isPasswordValid) {
                    null
                } else {
                    getString(R.string.login_invalid_password)
                }
            }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
