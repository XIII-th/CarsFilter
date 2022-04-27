package com.xiii_lab.carsfilter.builddtates.di

import com.xiii_lab.carsfilter.builddtates.data.BuildDatesRepository
import com.xiii_lab.carsfilter.builddtates.data.BuildDatesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by XIII-th on 27.04.2022
 */
@Module
@InstallIn(ViewModelComponent::class)
internal interface BuildDateModule {

    @Binds
    fun bindBuildDatesRepository(impl: BuildDatesRepositoryImpl): BuildDatesRepository
}