package com.example.flowmvihilt.di.modules

import com.example.flowmvihilt.data.remote.PhotoRepositoryImpl
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProviderImpl
import com.example.flowmvihilt.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun provideCoroutinesDispatchersProvider(coroutinesDispatchersProviderImpl: CoroutinesDispatchersProviderImpl) : CoroutinesDispatchersProvider

    @Binds
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository

}