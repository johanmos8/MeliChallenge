package com.johanmos8.data.repository

import com.johanmos8.data.remotedatasource.RemoteDataSource
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.repository.ItemRepository
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ItemRepository {
    override suspend fun getItemBySearch(search: String): Flow<Resource<List<ItemDetail>>> {
        return remoteDataSource.getItemBySearch(search)
    }

    override suspend fun getItemDetail(itemId: String): Resource<ItemDetail> {
        return remoteDataSource.getItemDetails(itemId)
    }
}