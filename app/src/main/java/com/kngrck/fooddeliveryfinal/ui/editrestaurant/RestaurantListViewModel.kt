package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getAllRestaurants(): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getAllRestaurants()

    fun deleteRestaurant(restaurantId: String): LiveData<Resource<BaseResponse>> =
        apiRepository.deleteRestaurant(restaurantId)
}