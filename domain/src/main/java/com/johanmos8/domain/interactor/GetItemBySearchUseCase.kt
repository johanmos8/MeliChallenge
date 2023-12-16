package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.ItemDomain
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetItemBySearchUseCase {
    suspend operator fun invoke(search: String) : Flow<Resource<List<ItemDomain>>>
}