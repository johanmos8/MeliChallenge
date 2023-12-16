package com.johanmos8.domain.di

import com.johanmos8.domain.interactor.GetItemBySearchUseCase
import com.johanmos8.domain.interactor.GetItemBySearchUseCaseImpl
import com.johanmos8.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InteractionModule {
    @Provides
    fun provideGetItemBySearchUseCase(
        itemRepository: ItemRepository
    ): GetItemBySearchUseCase = GetItemBySearchUseCaseImpl(itemRepository)

}