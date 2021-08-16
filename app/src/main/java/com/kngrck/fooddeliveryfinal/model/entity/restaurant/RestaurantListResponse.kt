package com.kngrck.fooddeliveryfinal.model.entity.restaurant

data class RestaurantListResponse(
    val data: List<Restaurant>,
    val success: Boolean
)