package com.kngrck.fooddeliveryfinal.model.entity.order

data class OrderListResponse(
    val data: List<Order>,
    val success: Boolean
)
