package com.xiii_lab.carsfilter.ui.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xiii_lab.carsfilter.navigation.MAIN_TYPE_NAME_ARG

/**
 * Created by XIII-th on 24.04.2022
 */
internal class SummaryViewModelImpl(
    stateHandle: SavedStateHandle
) : ViewModel(), SummaryViewModel {

    override val isDataSelected = stateHandle.contains(MAIN_TYPE_NAME_ARG)
}