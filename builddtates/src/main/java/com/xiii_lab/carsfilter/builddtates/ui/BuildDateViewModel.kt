package com.xiii_lab.carsfilter.builddtates.ui

import com.xiii_lab.carsfilter.design.list.ListState
import com.xiii_lab.carsfilter.remote.builddates.BuildDate
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface BuildDateViewModel {

    val toolbarTitle: String

    val toolbarSubtitle: String

    val buildDates: Flow<Pair<ListState, List<BuildDate>>>

    val selectedBuildDate: Flow<Triple<Manufacturer, MainType, BuildDate>>

    fun onSelected(buildDate: BuildDate)
}