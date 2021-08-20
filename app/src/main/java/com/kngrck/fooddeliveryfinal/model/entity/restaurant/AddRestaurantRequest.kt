package com.kngrck.fooddeliveryfinal.model.entity.restaurant

import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

data class AddRestaurantRequest(
    val name: String,
    val minimumFee: Double,
    val deliveryTime: String,
    val description: String,
    val category: String = "Other",
    val imageUrl: String = ""
)
