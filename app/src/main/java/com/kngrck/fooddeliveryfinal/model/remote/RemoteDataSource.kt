package com.kngrck.fooddeliveryfinal.model.remote


import com.kngrck.fooddeliveryfinal.model.entity.cart.AddCartRequest
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
import com.kngrck.fooddeliveryfinal.model.entity.favorite.AddFavoriteRestaurantRequest
import com.kngrck.fooddeliveryfinal.model.entity.meal.AddMealRequest
import com.kngrck.fooddeliveryfinal.model.entity.profile.UpdateProfileRequest
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.AddRestaurantRequest
import com.kngrck.fooddeliveryfinal.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    //RESTAURANT
    suspend fun getAllRestaurants() = getResult {
        apiService.getAllRestaurants()
    }

    suspend fun getRestaurantsByCategory(category: String) = getResult {
        apiService.getRestaurantsByCategory(category)
    }

    suspend fun getRestaurantById(id: String) = getResult {
        apiService.getRestaurantById(id)
    }

    suspend fun addRestaurant(addRestaurantRequest: AddRestaurantRequest) = getResult {
        apiService.addRestaurant(addRestaurantRequest)
    }

    suspend fun deleteRestaurant(restaurantId: String) = getResult {
        apiService.deleteRestaurant(restaurantId)
    }

    //MEAL
    suspend fun getMealById(id: String, restaurantId: String) = getResult {
        apiService.getMealById(id, restaurantId)
    }

    suspend fun addMeal(restaurantId: String, addMealRequest: AddMealRequest) = getResult {
        apiService.addMeal(restaurantId, addMealRequest)
    }

    suspend fun deleteMeal(restaurantId: String, mealId: String) = getResult {
        apiService.deleteMeal(restaurantId, mealId)
    }

    //PROFILE
    suspend fun getProfile() = getResult {
        apiService.getProfile()
    }

    suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest) = getResult {
        apiService.updateProfile(updateProfileRequest)
    }

    //CART
    suspend fun addToCart(addCartRequest: AddCartRequest) = getResult {
        apiService.addToCart(addCartRequest)
    }

    suspend fun getCart() = getResult {
        apiService.getCart()
    }

    suspend fun confirmCart() = getResult {
        apiService.confirmCart()
    }

    suspend fun updateCartOrderCount(cartOrderId: String, count: UpdateCartOrderCountRequest) =
        getResult {
            apiService.updateCartOrderCount(cartOrderId, count)
        }

    suspend fun deleteCartOrder(cartOrderId: String) = getResult {
        apiService.deleteCartOrder(cartOrderId)
    }

    //FAVORITE RESTAURANT
    suspend fun getFavoriteRestaurants() = getResult {
        apiService.getFavoriteRestaurants()
    }

    suspend fun addRestaurantToFavorite(addFavoriteRestaurantRequest: AddFavoriteRestaurantRequest) =
        getResult {
            apiService.addRestaurantToFavorite(addFavoriteRestaurantRequest)
        }

    suspend fun deleteFavoriteRestaurant(restaurantId: String) = getResult {
        apiService.deleteFavoriteRestaurant(restaurantId)
    }

}

