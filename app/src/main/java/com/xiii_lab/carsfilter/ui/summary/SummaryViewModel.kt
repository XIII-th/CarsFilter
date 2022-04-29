package com.xiii_lab.carsfilter.ui.summary

import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 24.04.2022
 */
internal interface SummaryViewModel {

    val isDataSelected: Boolean

    val manufacturer: String

    val mainType: String

    val buildDate: String

    val connectivityNotification: Flow<Unit>

    val requestNewFilter: Flow<Unit>

    fun onNewFilterRequested()
}