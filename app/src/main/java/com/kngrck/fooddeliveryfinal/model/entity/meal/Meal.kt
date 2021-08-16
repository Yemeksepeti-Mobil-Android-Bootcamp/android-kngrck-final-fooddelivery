package com.kngrck.fooddeliveryfinal.model.entity.meal

data class Meal(
    val name: String,
    val details: String,
    val count: Int = 0,
    val price: Double,
    val ingredients: ArrayList<Ingredient>
)
