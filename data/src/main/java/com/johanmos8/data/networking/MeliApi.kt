package com.johanmos8.data.networking

import com.johanmos8.data.networking.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MeliApi {

    @GET("sites/MLA/search")
    suspend fun getItemBySearch(
        @Query("q") query: String
    ): Response<SearchResponse>

}