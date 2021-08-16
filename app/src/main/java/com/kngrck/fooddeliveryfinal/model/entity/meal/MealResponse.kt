package com.kngrck.fooddeliveryfinal.model.entity.meal

import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

data class MealResponse(
    val data: Restaurant,
    val success: Boolean
)
