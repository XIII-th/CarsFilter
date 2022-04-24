package com.xiii_lab.carsfilter.manufacturer.di

import com.xiii_lab.carsfilter.manufacturer.data.ManufacturerRepository
import com.xiii_lab.carsfilter.manufacturer.data.ManufacturerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by XIII-th on 24.04.2022
 */
@Module
@InstallIn(ViewModelComponent::class)
internal interface ManufacturerModule {

    @Binds
    fun bindManufacturerRepository(impl: ManufacturerRepositoryImpl): ManufacturerRepository
}