package com.kngrck.fooddeliveryfinal.ui.favorite

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    apiRepository: ApiRepository
) : ViewModel() {
    fun getFavRestaurants(): ArrayList<Restaurant> {
        return arrayListOf(
            Restaurant(
                "1",
                "Ali Usta'nın lezzetli dönerleri ve bülbülü",
                9.2,
                15.0,
                "30-40 minutes",
                "Delicious Burgers"
            ),
            Restaurant("2", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
            Restaurant("3", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
            Restaurant("4", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
            Restaurant("5", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
            Restaurant("6", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
            Restaurant("7", "Burger King", 9.2, 15.0, "30-40 minutes", "Delicious Burgers"),
        )
    }
}