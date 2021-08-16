package com.kngrck.fooddeliveryfinal.model.entity.restaurant

data class Restaurant(
    val name: String,
    val rating: Double,
    val minimumFee: Double,
    val deliveryTime: String,
    val category: String = "Burger"
)
