package com.johanmos8.data.networking.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("thumbnail_id") val thumbnailId: String,
    @SerializedName("catalog_product_id") val catalogProductId: String,
    @SerializedName("listing_type_id") val listingTypeId: String,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("buying_mode") val buyingMode: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("order_backend") val orderBackend: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("sale_price") val salePrice: Double?,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("use_thumbnail_id") val useThumbnailId: Boolean,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean,
    @SerializedName("shipping") val shipping: Shipping,
    @SerializedName("stop_time") val stopTime: String,
    @SerializedName("seller") val seller: Seller,
    @SerializedName("attributes") val attributes: List<Attribute>,
    @SerializedName("winner_item_id") val winnerItemId: String?,
    @SerializedName("catalog_listing") val catalogListing: Boolean,
    @SerializedName("inventory_id") val inventoryId: String,
    @SerializedName("differential_pricing") val differentialPricing: DifferentialPricing?,
    @SerializedName("discounts") val discounts: String?,
    @SerializedName("installments") val installments: Installments?,



)