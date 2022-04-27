package com.xiii_lab.carsfilter.builddtates.data

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

/**
 * Created by XIII-th on 27.04.2022
 */
internal class BuildDatesRepositoryImpl @Inject constructor() : BuildDatesRepository {

    override fun getBuildDates(manufacturerId: String, mainTypeId: String) =
        // TODO: implement data loading
        MutableSharedFlow<List<BuildDate>>()
}