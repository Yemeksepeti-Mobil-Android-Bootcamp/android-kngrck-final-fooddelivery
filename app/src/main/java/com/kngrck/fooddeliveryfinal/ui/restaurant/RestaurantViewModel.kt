package com.kngrck.fooddeliveryfinal.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.favorite.AddFavoriteRestaurantRequest

import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantById(id: String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantById(id)

    fun addRestaurantToFavorite(addFavoriteRestaurantRequest: AddFavoriteRestaurantRequest): LiveData<Resource<BaseResponse>> =
        apiRepository.addRestaurantToFavorite(addFavoriteRestaurantRequest)
}