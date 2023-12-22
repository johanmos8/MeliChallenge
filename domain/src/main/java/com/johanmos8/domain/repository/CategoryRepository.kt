package com.johanmos8.domain.repository

import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.util.Resource

interface CategoryRepository {

    suspend fun getCategories(): Resource<List<ItemCategory>>
}