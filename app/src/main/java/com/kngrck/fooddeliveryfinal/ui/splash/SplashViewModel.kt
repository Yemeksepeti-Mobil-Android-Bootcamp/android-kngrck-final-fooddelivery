package com.kngrck.fooddeliveryfinal.ui.splash

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    fun getToken(): String? =
        apiRepository.getToken()


    fun saveToken(token: String) {
        apiRepository.saveToken(token)
    }
}