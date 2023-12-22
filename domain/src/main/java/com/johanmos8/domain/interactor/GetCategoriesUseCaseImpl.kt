package com.johanmos8.domain.interactor

import com.johanmos8.domain.model.ItemCategory
import com.johanmos8.domain.repository.CategoryRepository
import com.johanmos8.domain.util.Resource

class GetCategoriesUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override suspend fun invoke(): Resource<List<ItemCategory>> {
        return categoryRepository.getCategories()
    }
}