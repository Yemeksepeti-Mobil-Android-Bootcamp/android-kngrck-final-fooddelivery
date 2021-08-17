package com.kngrck.fooddeliveryfinal.model.entity.meal

data class Meal(
    val id: String,
    val name: String,
    val details: String,
    val imageUrl: String,
    val price: Double,
    val ingredients: ArrayList<String>
)
