package com.kngrck.fooddeliveryfinal.model.entity.order

import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

data class Order(
    val createdAt: String,
    val restaurantName: String,
    val mealName: String,
    val mealPrice: Double,
    val mealCount: Int,
    val mealImage: Int = R.drawable.ic_pizza,
)
