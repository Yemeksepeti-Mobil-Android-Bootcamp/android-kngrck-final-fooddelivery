package com.kngrck.fooddeliveryfinal.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    fun getLastOrdersOfUser(): LiveData<Resource<OrderListResponse>> =
        apiRepository.getLastOrdersOfUser()

    fun logOut() {
        apiRepository.removeToken()
    }
}