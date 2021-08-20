package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantMeals(restaurantId: String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantById(restaurantId)

    fun deleteMeal(restaurantId: String,mealId: String): LiveData<Resource<BaseResponse>> =
        apiRepository.deleteMeal(restaurantId,mealId)
}