package com.xiii_lab.carsfilter.maintypes.di

import com.xiii_lab.carsfilter.maintypes.data.MainTypesPagingSource
import com.xiii_lab.carsfilter.maintypes.data.MainTypesRepository
import com.xiii_lab.carsfilter.maintypes.data.MainTypesRepositoryImpl
import com.xiii_lab.carsfilter.maintypes.data.TypesPagingSourceFactory
import com.xiii_lab.carsfilter.remote.maintype.MainTypesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by XIII-th on 27.04.2022
 */
@Module
@InstallIn(ViewModelComponent::class)
internal class MainTypeModule {

    @Provides
    fun provideMainTypesRepository(pagingSourceFactory: TypesPagingSourceFactory): MainTypesRepository =
        MainTypesRepositoryImpl(pagingSourceFactory)

    @Provides
    fun provideMainTypesPagingSource(remoteDataSource: MainTypesRemoteDataSource): TypesPagingSourceFactory =
        TypesPagingSourceFactory { manufacturerId ->
            MainTypesPagingSource(manufacturerId, remoteDataSource)
        }
}