package com.kngrck.fooddeliveryfinal.model.entity.common

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("data")
    val data: String,
    @SerializedName("success")
    val success: Boolean
)