package com.kngrck.fooddeliveryfinal.ui.editmeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.meal.AddMealRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMealViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun addMeal(restaurantId: String, addMealRequest: AddMealRequest): LiveData<Resource<BaseResponse>> =
        apiRepository.addMeal(restaurantId,addMealRequest)
}