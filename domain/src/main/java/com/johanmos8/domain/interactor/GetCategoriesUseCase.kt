package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Resource

interface GetCategoriesUseCase {
    suspend operator fun invoke() : Resource<List<ItemCategory>>

}