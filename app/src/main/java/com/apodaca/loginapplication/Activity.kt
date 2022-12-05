package com.apodaca.loginapplication


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.apodaca.loginapplication.global.AuthenticationviewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class Activity : AppCompatActivity(R.layout.activity) {

    private val viewModel: AuthenticationviewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.logout.onEach {
                Timber.d("Loggin out!")
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment

                navHostFragment.navController.navigate(R.id.logOut)
            }.launchIn(this)
        }
    }
}