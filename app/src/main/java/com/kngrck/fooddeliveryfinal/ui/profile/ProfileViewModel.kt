package com.kngrck.fooddeliveryfinal.ui.profile

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    appRepository: ApiRepository
): ViewModel(){
    fun getDummyOrders():ArrayList<Order>{
        return arrayListOf(
            Order("August 15, 15:03","Burger King","Steak House Burger",30.0,1),
            Order("August 30, 15:03","McDonald's","Double Cheese Burger",30.0,1),
            Order("September 23, 15:03","Ali Usta'nın Yeri","Doner Kebab",150.0,10),
            Order("November 4, 15:03","Burger King","Steak House Burger",30.0,1),
            Order("January 23, 15:03","Burger King","Steak House Burger",30.0,1),
            Order("March 1, 15:03","Burger King","Steak House Burger",30.0,1),

        )
    }
}