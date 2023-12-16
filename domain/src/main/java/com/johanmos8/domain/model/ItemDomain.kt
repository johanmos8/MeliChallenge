package com.johanmos8.domain.model

data class ItemDomain(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val currencyId:String
)