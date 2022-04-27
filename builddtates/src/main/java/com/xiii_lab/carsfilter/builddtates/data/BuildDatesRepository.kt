package com.xiii_lab.carsfilter.builddtates.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface BuildDatesRepository {

    fun getBuildDates(manufacturerId: String, mainTypeId: String): Flow<List<BuildDate>>
}