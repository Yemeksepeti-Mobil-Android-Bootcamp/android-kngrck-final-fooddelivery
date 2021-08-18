package com.kngrck.fooddeliveryfinal.model.entity.cart

data class AddCartRequest(
    val restaurantId: String,
    val mealId: String,
    val count: Int,
    val ingredients: List<String>,

)