package com.johanmos8.data.networking.model

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX>
)