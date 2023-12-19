package com.johanmos8.data.remotedatasource

import android.util.Log
import com.johanmos8.data.mapper.ToDomainModel
import com.johanmos8.data.mapper.toDomain
import com.johanmos8.data.networking.MeliApi
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val meliApi: MeliApi
) {
    private val TAG = "RemoteDataSource"

    /**
     * Function to get items by search
     * @param search String
     * @return Flow<Resource<List<ItemDomain>>>
     */
    suspend fun getItemBySearch(search: String): Flow<Resource<List<ItemDetail>>> {
        return flow {
            emit(Resource.loading())
            try {
                val response = meliApi.getItemBySearch(
                    query = search
                )
                if (response.isSuccessful) {
                    response.body()?.results?.map {
                        it.ToDomainModel()
                    }?.let {
                        emit(Resource.success(it))
                    }
                } else {
                    emit(Resource.success(emptyList()))
                }
            } catch (e: Exception) {
                Log.d(TAG, "${e.message}")
                emit(Resource.error("An error occurred ${e.message}"))
            }
        }
    }

    suspend fun getItemDetails(
        itemId: String
    ): Resource<ItemDetail> {

       return try {
            val response = meliApi.getDetailsById(
                itemId = itemId
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it.toDomain())
                }?: Resource.error("No items found")
            } else {
                Resource.error("No item found")
            }
        } catch (e: Exception) {
            Log.d(TAG, "${e.message}")
            return Resource.error("An error occurred ${e.message}")
        }
    }

}