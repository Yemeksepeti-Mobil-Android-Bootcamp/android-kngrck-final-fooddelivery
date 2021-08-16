package com.kngrck.fooddeliveryfinal.ui.restaurant

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel(){

    fun getDummyRestaurant(): Restaurant{
        val meals = ArrayList<Meal>()
        for (i in 0 until 10){
            meals.add(Meal("name $i","details $i", 0, i.toDouble(), arrayListOf(
                "ingredient 1 $i",
                "ingredient 2 $i",
                "ingredient 3 $i",
                "ingredient 4$i",
            )))
        }

        return Restaurant("Restaurant Name",9.0,20.00,"30-40 minutes",meals,"Burger")
    }
}