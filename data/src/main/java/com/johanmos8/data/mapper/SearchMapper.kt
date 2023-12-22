package com.johanmos8.data.mapper

import com.johanmos8.data.networking.model.DetailsResponse
import com.johanmos8.data.networking.model.Item
import com.johanmos8.data.networking.model.Location
import com.johanmos8.data.networking.model.Picture
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.model.ItemPicture

fun DetailsResponse.toDomain() = ItemDetail(
    id = id,
    title = title,
    price = price,
    thumbnail = secureThumbnail,
    currencyId = currencyId,
    condition = condition,
    acceptsMercadoPago = acceptsMercadopago,
    availableQuantity = availableQuantity,
    freeShipping = shipping?.free_shipping ?: false,
    picturesUrl = pictures?.toPictureList(),
    location = location?.getAddress()
)

fun Picture.toDomain() = ItemPicture(
    secureUrl = secureUrl,
    id = id,
    url = url,
    size = size,
    maxSize = maxSize,
    quality = quality
)

fun List<Picture?>.toPictureList(): List<ItemPicture> {
    return mapNotNull { it?.toDomain() }

}

fun Item.ToDomainModel() = ItemDetail(
    id = id,
    title = title,
    price = price,
    thumbnail = thumbnail,
    currencyId = currencyId,
    condition = condition,
    acceptsMercadoPago = acceptsMercadoPago,
    availableQuantity = availableQuantity,
    freeShipping = shipping.free_shipping,
    picturesUrl = emptyList()
)

fun Location?.getAddress(): String? {

    if (this == null) {
        return null
    }
    return "${this.city?.name ?: ""} - ${this.state?.name ?: ""} - ${this.country?.name ?: ""}".takeIf { it.isNotBlank() }}



