package com.kngrck.fooddeliveryfinal.model.entity.restaurant

import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

data class Restaurant(
    val id: String,
    val name: String,
    val rating: Double,
    val minimumFee: Double,
    val deliveryTime: String,
    val description: String,
    val meals: ArrayList<Meal>? = null,
    val category: String = "Other",
    val imageUrl: String = ""
)
