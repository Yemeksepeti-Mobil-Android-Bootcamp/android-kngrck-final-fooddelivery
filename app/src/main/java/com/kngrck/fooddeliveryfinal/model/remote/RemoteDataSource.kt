package com.kngrck.fooddeliveryfinal.model.remote


import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    //RESTAURANT
    suspend fun getAllRestaurants() = getResult {
        apiService.getAllRestaurants()
    }

    suspend fun getRestaurantsByType(type: String) = getResult {
        apiService.getRestaurantsByType(type)
    }

    suspend fun getRestaurantById(id: String) = getResult {
        apiService.getRestaurantById(id)
    }

    suspend fun addRestaurant(restaurant: Restaurant) = getResult {
        apiService.addRestaurant(restaurant)
    }

    //MEAL
    suspend fun getMealById(id: String) = getResult {
        apiService.getMealById(id)
    }

    suspend fun addMeal(restaurantId: String, meal: Meal) = getResult {
        apiService.addMeal(restaurantId, meal)
    }

    //ORDER
    suspend fun getAllOrdersOfUser() = getResult {
        apiService.getAllOrdersOfUser()
    }

    suspend fun addOrder(order: Order) = getResult {
        apiService.addOrder(order)
    }
}

