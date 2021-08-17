package com.kngrck.fooddeliveryfinal.model.entity.order

data class OrderListResponse(
    val data: ArrayList<Order>,
    val success: Boolean
)
