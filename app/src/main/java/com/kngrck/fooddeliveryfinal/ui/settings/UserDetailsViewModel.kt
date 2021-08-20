package com.kngrck.fooddeliveryfinal.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.profile.ProfileResponse
import com.kngrck.fooddeliveryfinal.model.entity.profile.UpdateProfileRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getProfile(): LiveData<Resource<ProfileResponse>> =
        apiRepository.getProfile()

    fun updateProfile(updateProfileRequest: UpdateProfileRequest) =
        apiRepository.updateProfile(updateProfileRequest)
}