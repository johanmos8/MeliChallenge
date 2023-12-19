package com.johanmos8.domain.repository

import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getItemBySearch(search: String): Flow<Resource<List<ItemDetail>>>

    suspend fun getItemDetail(itemId: String): Resource<ItemDetail>

}