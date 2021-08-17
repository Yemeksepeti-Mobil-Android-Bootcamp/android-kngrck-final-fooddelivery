package com.kngrck.fooddeliveryfinal.model.entity.restaurant

data class RestaurantListResponse(
    val data: ArrayList<Restaurant>,
    val success: Boolean
)