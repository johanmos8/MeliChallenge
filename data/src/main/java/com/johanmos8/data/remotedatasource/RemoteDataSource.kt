package com.johanmos8.data.remotedatasource

import com.johanmos8.data.mapper.toDomain
import com.johanmos8.data.networking.MeliApi
import com.johanmos8.domain.model.ItemDomain
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val meliApi: MeliApi
) {

    suspend fun getItemBySearch(search: String): Flow<Resource<List<ItemDomain>>> {
        return flow {
            emit(Resource.loading())
            try {
                val response = meliApi.getItemBySearch(
                  "Motorola"
                )
                if (response.isSuccessful) {
                    response.body()?.results?.map {
                        it.toDomain()
                    }?.let {
                        emit(Resource.success(it))
                    }
                } else {
                    emit(Resource.success(emptyList()))
                }
            } catch (e: Exception) {
                emit(Resource.error("An error occurred ${e.message}",))
            }
        }
    }
}