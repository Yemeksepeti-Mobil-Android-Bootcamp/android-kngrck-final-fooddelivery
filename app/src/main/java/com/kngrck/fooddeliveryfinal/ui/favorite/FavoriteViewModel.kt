package com.kngrck.fooddeliveryfinal.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getFavoriteRestaurants(): LiveData<Resource<RestaurantListResponse>> = apiRepository.getFavoriteRestaurants()

    fun deleteFavoriteRestaurant(restaurantId:String): LiveData<Resource<BaseResponse>> = apiRepository.deleteFavoriteRestaurant(restaurantId)
}