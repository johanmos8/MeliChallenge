package com.johanmos8.data.networking.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("available_filters") val availableFilters: List<AvailableFilter>,
    @SerializedName("available_sorts") val availableSorts: List<AvailableSort>,
    @SerializedName("country_default_time_zone") val countryDefaultTimeZone: String,
    @SerializedName("filters") val filters: List<Filter>,
    @SerializedName("results") val paging: Paging,
    @SerializedName("results") val results: List<Result>,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("sort") val sort: Sort
)