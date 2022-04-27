package com.xiii_lab.carsfilter.builddtates.ui

import com.xiii_lab.carsfilter.builddtates.data.BuildDate
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface BuildDateViewModel {

    val buildDates: Flow<List<BuildDate>>

    // TODO: Documentation
    val selectedBuildDate: Flow<Triple<String, String, String>>

    fun onSelected(buildDate: BuildDate)
}