package com.kngrck.fooddeliveryfinal.model.remote

import com.kngrck.fooddeliveryfinal.model.entity.cart.AddCartRequest
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.favorite.AddFavoriteRestaurantRequest
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.meal.MealResponse
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    //RESTAURANT
    @GET("restaurant")
    suspend fun getAllRestaurants(): Response<RestaurantListResponse>

    @GET("restaurant/category/{category}")
    suspend fun getRestaurantsByCategory(@Path("category") category: String): Response<RestaurantListResponse>

    @GET("restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @POST("restaurant")
    suspend fun addRestaurant(@Body request: Restaurant): Response<BaseResponse>

    //MEAL
    @GET("restaurant/{restaurantId}/meal/{id}")
    suspend fun getMealById(@Path("id") id: String, @Path("restaurantId") restaurantId: String): Response<MealResponse>

    @POST("restaurant/{restaurantId}/meal")
    suspend fun addMeal(
        @Path("restaurantId") restaurantId: String,
        @Body request: Meal
    ): Response<BaseResponse>

    //ORDER
    @GET("orders")
    suspend fun getLastOrdersOfUser(): Response<OrderListResponse>

    @POST("order")
    suspend fun addOrder(@Body request: Order): Response<BaseResponse>

    //CART
    @GET("cart")
    suspend fun getCart(): Response<OrderListResponse>

    @GET("cart/order")
    suspend fun confirmCart(): Response<BaseResponse>

    @POST("cart")
    suspend fun addToCart(@Body request: AddCartRequest): Response<BaseResponse>

    @PATCH("cart/{cartOrderId}/count")
    suspend fun updateCartOrderCount(@Path("cartOrderId") cartOrderId: String,@Body request: UpdateCartOrderCountRequest): Response<BaseResponse>

    @DELETE("cart/{cartOrderId}")
    suspend fun deleteCartOrder(@Path("cartOrderId") cartOrderId: String): Response<BaseResponse>

    //FAVORITE RESTAURANTS
    @GET("favorite")
    suspend fun getFavoriteRestaurants(): Response<RestaurantListResponse>

    @POST("favorite")
    suspend fun addRestaurantToFavorite(@Body request: AddFavoriteRestaurantRequest): Response<BaseResponse>

    @DELETE("favorite/{restaurantId}")
    suspend fun deleteFavoriteRestaurant(@Path("restaurantId") restaurantId: String): Response<BaseResponse>
}