package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.repository.ItemRepository
import com.johanmos8.domain.util.Resource
import javax.inject.Inject

class GetItemDetailsUseCaseImpl @Inject constructor(
    private val itemRepository: ItemRepository
) : GetItemDetailsUseCase {
    override suspend fun invoke(itemId: String): Resource<ItemDetail> {
        return itemRepository.getItemDetail(itemId)
    }

}