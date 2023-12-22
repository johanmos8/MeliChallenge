package com.johanmos8.data.di

import com.johanmos8.data.networking.MeliApi
import com.johanmos8.data.remotedatasource.RemoteDataSource
import com.johanmos8.data.repository.CategoryRepositoryImpl
import com.johanmos8.data.repository.ItemRepositoryImpl
import com.johanmos8.domain.repository.CategoryRepository
import com.johanmos8.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRemoteDataSource(
        meliApi: MeliApi
    ): RemoteDataSource = RemoteDataSource(meliApi)


    @Provides
    fun provideItemRepository(
        remoteDataSource: RemoteDataSource
    ): ItemRepository = ItemRepositoryImpl(remoteDataSource)

    @Provides
    fun provideCategoryRepository(
        remoteDataSource: RemoteDataSource
    ): CategoryRepository = CategoryRepositoryImpl(
        remoteDataSource
    )
}