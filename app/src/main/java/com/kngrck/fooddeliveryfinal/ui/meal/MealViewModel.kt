package com.kngrck.fooddeliveryfinal.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.meal.Ingredient
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.meal.MealResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MealViewModel @Inject constructor(
    private var apiRepository: ApiRepository
):ViewModel() {
    fun getMealById(id: String,restaurantId: String): LiveData<Resource<MealResponse>> = apiRepository.getMealById(id,restaurantId)
}