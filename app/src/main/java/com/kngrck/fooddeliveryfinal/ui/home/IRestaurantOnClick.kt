package com.kngrck.fooddeliveryfinal.ui.home

import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

interface IRestaurantOnClick {
    fun onRestaurantClick(restaurant: Restaurant)
}