package com.johanmos8.data.util

const val BASE_URL = "https://api.mercadolibre.com"
private const val SITE_ID = "MCO"

//ENDPOINTS
const val GET_ITEMS_BY_SEARCH = "sites/$SITE_ID/search"
const val GET_ITEM_DETAILS = "items/{itemId}"
const val GET_CATEGORIES = "sites/$SITE_ID/categories"
const val GET_DESCRIPTION = "items/{productId}/description"