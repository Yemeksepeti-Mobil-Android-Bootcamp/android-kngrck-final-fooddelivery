package com.kngrck.fooddeliveryfinal.model

import androidx.lifecycle.LiveData
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.meal.MealResponse
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantResponse
import com.kngrck.fooddeliveryfinal.model.local.LocalDataSource
import com.kngrck.fooddeliveryfinal.model.remote.RemoteDataSource
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
    //RESTAURANT
    fun getAllRestaurants(): LiveData<Resource<RestaurantListResponse>> =
        performNetworkOperation { remoteDataSource.getAllRestaurants() }

    fun getRestaurantsByType(type: String): LiveData<Resource<RestaurantListResponse>> =
        performNetworkOperation { remoteDataSource.getRestaurantsByType(type) }

    fun getRestaurantById(id: String): LiveData<Resource<RestaurantResponse>> =
        performNetworkOperation { remoteDataSource.getRestaurantById(id) }

    fun addRestaurant(restaurant: Restaurant): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addRestaurant(restaurant) }

    //MEAL
    fun getMealById(id: String): LiveData<Resource<MealResponse>> =
        performNetworkOperation { remoteDataSource.getMealById(id) }

    fun addMeal(restaurantId: String, meal: Meal): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addMeal(restaurantId, meal) }

    //ORDER
    fun getAllOrdersOfUser(): LiveData<Resource<OrderListResponse>> =
        performNetworkOperation { remoteDataSource.getAllOrdersOfUser() }

    fun addOrder(order: Order): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addOrder(order) }
}
