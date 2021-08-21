package com.kngrck.fooddeliveryfinal.ui.settings

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun logOut() {
        apiRepository.removeToken()
    }
}