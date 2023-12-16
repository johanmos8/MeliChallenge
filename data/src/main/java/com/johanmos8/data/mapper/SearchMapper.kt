package com.johanmos8.data.mapper

import com.johanmos8.data.networking.model.Item
import com.johanmos8.domain.model.ItemDomain

fun Item.toDomain() = ItemDomain(
    id = id,
    title = title,
    price = price,
    thumbnail = thumbnail,
    currencyId = currencyId
)