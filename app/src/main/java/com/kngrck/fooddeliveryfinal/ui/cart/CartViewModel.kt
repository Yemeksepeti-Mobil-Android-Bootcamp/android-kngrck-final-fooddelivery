package com.kngrck.fooddeliveryfinal.ui.cart

import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    apiRepository: ApiRepository
) : ViewModel() {

    fun getDummyCartOrders(): ArrayList<Order> {
        return arrayListOf(
            Order(
                1231245125,
                "Burger King",
                "Steak House Burger",
                30.0,
                1,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
            Order(
                1231245125,
                "McDonald's",
                "Double Cheese Burger",
                30.0,
                1,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
            Order(
                1231245125,
                "Ali Usta'nın Yeri",
                "Doner Kebab",
                150.0,
                10,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
            Order(
                1231245125,
                "Burger King",
                "Steak House Burger",
                30.0,
                1,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
            Order(
                1231245125,
                "Burger King",
                "Steak House Burger",
                30.0,
                1,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
            Order(
                1231245125,
                "Burger King",
                "Steak House Burger",
                30.0,
                1,
                "https://www.burgerking.com.tr/cmsfiles/products/bk-steakhouse-burger-menu.png?v=173"
            ),
        )
    }

}