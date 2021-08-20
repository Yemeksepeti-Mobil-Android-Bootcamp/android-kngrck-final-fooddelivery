package com.kngrck.fooddeliveryfinal.model.entity.meal

data class AddMealRequest(
    val name: String,
    val details: String,
    val imageUrl: String,
    val price: Double,
    val ingredients: ArrayList<String>
)
