package com.johanmos8.data.networking

import com.johanmos8.data.networking.model.Category
import com.johanmos8.data.networking.model.DetailsResponse
import com.johanmos8.data.networking.model.SearchResponse
import com.johanmos8.data.util.GET_CATEGORIES
import com.johanmos8.data.util.GET_ITEMS_BY_SEARCH
import com.johanmos8.data.util.GET_ITEM_DETAILS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliApi {

    @GET(GET_ITEMS_BY_SEARCH)
    suspend fun getItemBySearch(
        @Query("q") query: String
    ): Response<SearchResponse>

    @GET(GET_CATEGORIES)
    suspend fun getCategories(): Response<List<Category>>

    @GET(GET_ITEM_DETAILS)
    suspend fun getDetailsById(
        @Path("itemId") itemId: String
    ): Response<DetailsResponse>
}