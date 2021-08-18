package com.kngrck.fooddeliveryfinal.model.entity.order

data class Order(
    val id: String,
    val createdAt: Long?,
    val restaurantName: String,
    val mealName: String,
    val mealPrice: Double,
    var count: Int,
    val mealImage: String,
)
