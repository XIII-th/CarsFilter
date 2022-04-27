package com.xiii_lab.carsfilter.ui.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xiii_lab.carsfilter.navigation.BUILD_DATE_ARG
import com.xiii_lab.carsfilter.navigation.MAIN_TYPE_NAME_ARG
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_NAME_ARG

/**
 * Created by XIII-th on 24.04.2022
 */
internal class SummaryViewModelImpl(
    stateHandle: SavedStateHandle
) : ViewModel(), SummaryViewModel {

    override val isDataSelected = stateHandle.contains(MANUFACTURER_NAME_ARG)

    override val manufacturer: String = stateHandle[MANUFACTURER_NAME_ARG] ?: ""

    override val mainType: String = stateHandle[MAIN_TYPE_NAME_ARG] ?: ""

    override val buildDate: String = stateHandle[BUILD_DATE_ARG] ?: ""
}