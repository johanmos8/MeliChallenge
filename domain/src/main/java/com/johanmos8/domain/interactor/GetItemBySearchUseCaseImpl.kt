package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.Item
import com.johanmos8.domain.repository.ItemRepository
import com.johanmos8.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemBySearchUseCaseImpl @Inject constructor(
    private val itemRepository: ItemRepository
) : GetItemBySearchUseCase {
    override suspend fun invoke(search: String): Flow<Resource<List<Item>>> {
        return itemRepository.getItemBySearch(search)
    }
}