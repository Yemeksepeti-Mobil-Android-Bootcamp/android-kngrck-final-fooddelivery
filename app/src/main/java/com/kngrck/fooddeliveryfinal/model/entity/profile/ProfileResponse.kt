package com.kngrck.fooddeliveryfinal.model.entity.profile

import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

data class ProfileResponse(
    val data: Profile,
    val success: Boolean
)
