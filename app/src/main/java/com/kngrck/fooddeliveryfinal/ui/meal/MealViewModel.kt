package com.kngrck.fooddeliveryfinal.ui.meal

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.meal.Ingredient
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MealViewModel @Inject constructor(
    apiRepository: ApiRepository
):ViewModel() {
    fun getDummyMeal():Meal{
        return Meal("Hamburger","Tomato, Onion,Pickles, Cheddar, 180g Meat with fries and drink.",0,40.00, arrayListOf(
            Ingredient("Tomato"),
            Ingredient("Onion"),
            Ingredient("Pickles"),
        ))
    }
}