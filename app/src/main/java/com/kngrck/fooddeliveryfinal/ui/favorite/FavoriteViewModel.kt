package com.kngrck.fooddeliveryfinal.ui.favorite

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.meal.Ingredient
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    apiRepository: ApiRepository
): ViewModel(){
    fun getFavRestaurants(): ArrayList<Restaurant>{
        return arrayListOf(
            Restaurant("Ali Usta'nın lezzetli dönerleri ve bülbülü",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
            Restaurant("Burger King",9.2,15.0,"30-40 minutes"),
        )
    }
}