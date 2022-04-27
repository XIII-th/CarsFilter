package com.xiii_lab.carsfilter.builddtates.data

import com.xiii_lab.carsfilter.remote.builddates.BuildDate
import com.xiii_lab.carsfilter.remote.builddates.BuildDatesRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by XIII-th on 27.04.2022
 */
internal class BuildDatesRepositoryImpl @Inject constructor(
    private val buildDatesRemoteDataSource: BuildDatesRemoteDataSource
) : BuildDatesRepository {

    override fun getBuildDates(manufacturerId: String, mainTypeId: String) = flow {
        val dates = buildDatesRemoteDataSource.getBuildDates(manufacturerId, mainTypeId)
            .buildDates.entries.map { (id, date) ->
                BuildDate(id, date)
            }
        emit(dates)
        // TODO: Handle errors
    }
    // TODO: .cachedIn()
}