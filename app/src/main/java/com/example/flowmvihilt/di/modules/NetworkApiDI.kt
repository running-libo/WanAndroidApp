package com.example.flowmvihilt.di.modules

import com.example.flowmvihilt.data.remote.ISystemService
import com.example.flowmvihilt.data.remote.MainApiService
import com.example.flowmvihilt.data.remote.QaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * 向外提供Retrofit的各个Apiservice
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkApiDI {

    @Provides
    fun mainApi(retrofit: Retrofit): MainApiService {
        return MainApiService(retrofit)
    }

    @Provides
    fun sysApi(retrofit: Retrofit): ISystemService {
        return ISystemService(retrofit)
    }

    @Provides
    fun qaApi(retrofit: Retrofit): QaService {
        return QaService(retrofit)
    }

}