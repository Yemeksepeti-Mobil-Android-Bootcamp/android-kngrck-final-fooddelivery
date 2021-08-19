package com.kngrck.fooddeliveryfinal.ui.favorite

import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

interface IOnDeleteRestaurant {
    fun onDeleteRestaurant(restaurantId: String)
}