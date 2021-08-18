package com.kngrck.fooddeliveryfinal.model.remote

import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.model.entity.meal.MealResponse
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


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


}