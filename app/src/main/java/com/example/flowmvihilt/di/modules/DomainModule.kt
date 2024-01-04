package com.example.flowmvihilt.di.modules

import com.example.flowmvihilt.domain.repository.MainRepositoryImpl
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProviderImpl
import com.example.flowmvihilt.domain.repository.MainRepository
import com.example.flowmvihilt.domain.repository.QaRepository
import com.example.flowmvihilt.domain.repository.QaRepositoryImpl
import com.example.flowmvihilt.domain.repository.SysRepository
import com.example.flowmvihilt.domain.repository.SysRepositoryImpl
import com.example.flowmvihilt.domain.repository.WxArticalRepository
import com.example.flowmvihilt.domain.repository.WxArticalRepositoryImpl
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
    fun providePhotoRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    fun provideSysRepository(sysRepositoryImpl: SysRepositoryImpl): SysRepository

    @Binds
    fun provideQaRepository(qaRepositoryImpl: QaRepositoryImpl): QaRepository

    @Binds
    fun provideWxRepository(wxArticalRepository: WxArticalRepositoryImpl): WxArticalRepository

}