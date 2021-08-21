package com.kngrck.fooddeliveryfinal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.RestaurantListResponse
import com.kngrck.fooddeliveryfinal.model.helper.Category
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {
    var restaurantList = ArrayList<Restaurant>()

    fun getAllRestaurants(): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getAllRestaurants()


    fun getRestaurantsByCategory(category: String): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getRestaurantsByCategory(category)

    fun searchRestaurant(text: String?): ArrayList<Restaurant> {

        if (text.isNullOrEmpty()) return restaurantList

        val filterList = ArrayList<Restaurant>()
        restaurantList.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)

        }
        return filterList
    }

    fun getCategories(): ArrayList<Category> {
        return arrayListOf(
            Category(null, "All"),
            Category(R.drawable.ic_burger, "Burger"),
            Category(R.drawable.ic_pizza, "Pizza"),
            Category(R.drawable.ic_kebab, "Kebab"),
            Category(R.drawable.ic_world, "World"),
            Category(R.drawable.ic_sushi3, "Eastern"),
            Category(R.drawable.ic_fish, "Seafood"),
            Category(R.drawable.ic_dessert, "Dessert"),
            Category(null, "Other"),
        )
    }
}