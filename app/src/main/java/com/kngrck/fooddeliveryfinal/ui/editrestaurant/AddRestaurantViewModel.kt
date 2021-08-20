package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.AddRestaurantRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRestaurantViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun addRestaurant(addRestaurantRequest: AddRestaurantRequest): LiveData<Resource<BaseResponse>> =
        apiRepository.addRestaurant(addRestaurantRequest)

}