package com.johanmos8.data.remotedatasource

import com.johanmos8.data.networking.MeliApi
import com.johanmos8.domain.model.Item
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val meliApi: MeliApi
) {

    suspend fun getItemBySearch(search: String): Flow<Resource<List<Item>>>  {
        return flow {
            emit(Resource.loading())
            try {
                val response = meliApi.getItemBySearch(
                    url = "",
                    search = "MLA1055"
                )
                if (response.isSuccessful) {
                    TODO()
                } else {
                    emit(Resource.success(emptyList()))
                }
            } catch (e: Exception) {
                emit(Resource.error("An error occurred", null))
            }
        }
    }
}