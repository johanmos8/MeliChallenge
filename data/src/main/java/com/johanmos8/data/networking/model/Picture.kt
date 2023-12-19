package com.johanmos8.data.networking.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("secure_url")
    val secureUrl: String?,
)