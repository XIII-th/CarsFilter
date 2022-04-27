package com.xiii_lab.carsfilter.builddtates.data

import com.xiii_lab.carsfilter.remote.builddates.BuildDate
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface BuildDatesRepository {

    fun getBuildDates(manufacturerId: String, mainTypeId: String): Flow<List<BuildDate>>
}