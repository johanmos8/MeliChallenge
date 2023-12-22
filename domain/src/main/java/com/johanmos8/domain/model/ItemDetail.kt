package com.johanmos8.domain.model

data class ItemDetail(
    val id: String,
    val title: String,
    val price: Double?,
    val thumbnail: String?,
    val currencyId: String,
    val condition: String,
    val acceptsMercadoPago: Boolean?,
    val availableQuantity: Int?,
    val description: String?="",
    val freeShipping: Boolean,
    val location: String?="",
    val pictureUrl: String?="",
    val picturesUrl: List<ItemPicture>?,

    )
