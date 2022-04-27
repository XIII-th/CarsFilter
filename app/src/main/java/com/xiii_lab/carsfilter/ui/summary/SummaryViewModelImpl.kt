package com.xiii_lab.carsfilter.ui.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_ID_ARG

/**
 * Created by XIII-th on 24.04.2022
 */
internal class SummaryViewModelImpl(
    stateHandle: SavedStateHandle
) : ViewModel(), SummaryViewModel {

    override val isDataSelected = stateHandle.contains(MANUFACTURER_ID_ARG)
}