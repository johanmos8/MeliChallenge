package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.util.Resource

interface GetItemDetailsUseCase {
    suspend operator fun invoke(itemId: String): Resource<ItemDetail>
}