package com.kngrck.fooddeliveryfinal.model

import androidx.lifecycle.LiveData
import com.kngrck.fooddeliveryfinal.model.entity.cart.AddCartRequest
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.favorite.AddFavoriteRestaurantRequest
import com.kngrck.fooddeliveryfinal.model.entity.meal.AddMealRequest
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.meal.MealResponse
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.model.entity.profile.Profile
import com.kngrck.fooddeliveryfinal.model.entity.profile.ProfileResponse
import com.kngrck.fooddeliveryfinal.model.entity.profile.UpdateProfileRequest
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.AddRestaurantRequest
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

    fun addRestaurant(addRestaurantRequest: AddRestaurantRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addRestaurant(addRestaurantRequest) }

    fun deleteRestaurant(restaurantId: String): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.deleteRestaurant(restaurantId) }

    //MEAL
    fun getMealById(id: String, restaurantId: String): LiveData<Resource<MealResponse>> =
        performNetworkOperation { remoteDataSource.getMealById(id, restaurantId) }

    fun addMeal(restaurantId: String, addMealRequest: AddMealRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addMeal(restaurantId, addMealRequest) }

    fun deleteMeal(restaurantId: String,mealId: String): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.deleteMeal(restaurantId,mealId) }

    //ORDER


    fun addOrder(order: Order): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addOrder(order) }

    //PROFILE
    fun getProfile(): LiveData<Resource<ProfileResponse>> =
        performNetworkOperation { remoteDataSource.getProfile() }

    fun updateProfile(updateProfileRequest: UpdateProfileRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.updateProfile(updateProfileRequest) }

    //CART
    fun addToCart(addCartRequest: AddCartRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.addToCart(addCartRequest) }

    fun getCart(): LiveData<Resource<OrderListResponse>> =
        performNetworkOperation { remoteDataSource.getCart() }

    fun confirmCart(): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.confirmCart() }

    fun updateCartOrderCount(
        cartOrderId: String,
        count: UpdateCartOrderCountRequest
    ): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.updateCartOrderCount(cartOrderId, count) }

    fun deleteCartOrder(cartOrderId: String): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.deleteCartOrder(cartOrderId) }

    //FAVORITE RESTAURANTS

    fun getFavoriteRestaurants(): LiveData<Resource<RestaurantListResponse>> =
        performNetworkOperation { remoteDataSource.getFavoriteRestaurants() }

    fun addRestaurantToFavorite(addFavoriteRestaurantRequest: AddFavoriteRestaurantRequest): LiveData<Resource<BaseResponse>> =
        performNetworkOperation {
            remoteDataSource.addRestaurantToFavorite(
                addFavoriteRestaurantRequest
            )
        }

    fun deleteFavoriteRestaurant(restaurantId: String): LiveData<Resource<BaseResponse>> =
        performNetworkOperation { remoteDataSource.deleteFavoriteRestaurant(restaurantId) }

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
