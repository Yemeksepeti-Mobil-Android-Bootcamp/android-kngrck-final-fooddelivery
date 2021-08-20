package com.kngrck.fooddeliveryfinal.model.entity.profile

import com.google.gson.annotations.SerializedName
import com.kngrck.fooddeliveryfinal.model.entity.order.Order

data class Profile(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("orders")
    val orders: ArrayList<Order>
)
