package com.kngrck.fooddeliveryfinal.ui.restaurant

import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

interface IMealOnClick {
    fun onClick(meal: Meal)
}