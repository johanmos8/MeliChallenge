package com.johanmos8.data.repository

import com.johanmos8.data.remotedatasource.RemoteDataSource
import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.repository.CategoryRepository
import com.johanmos8.domain.util.Resource
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): CategoryRepository {
    override suspend fun getCategories(): Resource<List<ItemCategory>> {
        return remoteDataSource.getCategories()
    }
}