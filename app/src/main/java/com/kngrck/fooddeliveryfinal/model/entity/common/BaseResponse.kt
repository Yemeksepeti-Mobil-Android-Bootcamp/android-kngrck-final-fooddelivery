package com.kngrck.fooddeliveryfinal.model.entity.common

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)