package com.kngrck.fooddeliveryfinal.model.entity.restaurant

import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

data class Restaurant(
    val name: String,
    val rating: Double,
    val minimumFee: Double,
    val deliveryTime: String,
    val meals: ArrayList<Meal>? = null,
    val category: String = "Burger",
)
