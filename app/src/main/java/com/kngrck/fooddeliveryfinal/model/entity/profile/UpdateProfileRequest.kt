package com.kngrck.fooddeliveryfinal.model.entity.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    val name: String?,
    val phone: String?,
    val profileImage: String?,
)
