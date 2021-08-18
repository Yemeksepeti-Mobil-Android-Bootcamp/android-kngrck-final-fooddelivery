package com.kngrck.fooddeliveryfinal.model

import androidx.lifecycle.LiveData
import com.kngrck.fooddeliveryfinal.model.entity.cart.AddCartRequest
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
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

    fun getRestaurantsByCategory(category: String): LiveData<Resource<RestaurantListResponse>> =
        performNetworkOperation { remoteDataSource.getRestaurantsByCategory(category) }

    fun getRestaurantById(id: String): LiveData<Resource<RestaurantResponse>> =
        performNetworkOperation { remoteDataSource.getRestaurantById(id) }

    fun addRestaurant(restaurant: Restaurant): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addRestaurant(restaurant) }

    //MEAL
    fun getMealById(id: String, restaurantId: String): LiveData<Resource<MealResponse>> =
        performNetworkOperation { remoteDataSource.getMealById(id, restaurantId) }

    fun addMeal(restaurantId: String, meal: Meal): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addMeal(restaurantId, meal) }

    //ORDER
    fun getLastOrdersOfUser(): LiveData<Resource<OrderListResponse>> =
        performNetworkOperation { remoteDataSource.getLastOrdersOfUser() }

    fun addOrder(order: Order): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addOrder(order) }

    //CART

    fun addToCart(addCartRequest: AddCartRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addToCart(addCartRequest) }

    fun getCart(): LiveData<Resource<OrderListResponse>> =
        performNetworkOperation { remoteDataSource.getCart() }

    fun confirmCart(): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.confirmCart() }

    fun updateCartOrderCount(cartOrderId: String,count: UpdateCartOrderCountRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.updateCartOrderCount(cartOrderId,count) }

    fun deleteCartOrder(cartOrderId: String): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.deleteCartOrder(cartOrderId) }

    //Token
    fun getToken(): String? =
        localDataSource.getToken()

    fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    fun removeToken() {
        localDataSource.saveToken("")
    }


}
