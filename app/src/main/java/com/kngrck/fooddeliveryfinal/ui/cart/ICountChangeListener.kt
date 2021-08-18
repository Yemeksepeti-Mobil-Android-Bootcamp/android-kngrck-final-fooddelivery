package com.kngrck.fooddeliveryfinal.ui.cart

import com.kngrck.fooddeliveryfinal.model.entity.order.Order

interface ICountChangeListener {
    fun countChanged(cartOrderId: String,count: Int, updatedOrders: ArrayList<Order>)
}