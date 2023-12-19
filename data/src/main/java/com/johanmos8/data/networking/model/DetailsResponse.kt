package com.johanmos8.data.networking.model


import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    val condition: String,
    val shipping: Shipping?,
    val id: String,
    val pictures: List<Picture?>?,
    val price: Double?,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String?,
    val title: String
)
