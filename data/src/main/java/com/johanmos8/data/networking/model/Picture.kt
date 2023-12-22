package com.johanmos8.data.networking.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("secure_url")
    val secureUrl: String?,
    val id: String,
    val url: String?,
    val size: String?,
    @SerializedName("max_size")
    val maxSize: String?,
    val quality: String?
)