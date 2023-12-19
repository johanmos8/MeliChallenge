package com.johanmos8.data.mapper

import com.johanmos8.data.networking.model.DetailsResponse
import com.johanmos8.data.networking.model.Item
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
    picturesUrl = pictures?.toStringList()
)

fun Picture.toDomain() = ItemPicture(secureUrl = secureUrl)

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

fun List<Picture?>.toStringList(): List<String> {
    return mapNotNull { it?.secureUrl }
}
