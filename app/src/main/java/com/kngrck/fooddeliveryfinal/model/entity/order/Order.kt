package com.kngrck.fooddeliveryfinal.model.entity.order

data class Order(
    val createdAt: Long,
    val restaurantName: String,
    val mealName: String,
    val mealPrice: Double,
    val count: Int,
    val mealImage: String,
)
