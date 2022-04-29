package com.xiii_lab.carsfilter.ui.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSource
import com.xiii_lab.carsfilter.navigation.BUILD_DATE_ARG
import com.xiii_lab.carsfilter.navigation.MAIN_TYPE_NAME_ARG
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_NAME_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
@HiltViewModel
internal class SummaryViewModelImpl @Inject constructor(
    stateHandle: SavedStateHandle,
    private val connectivityInfoDataSource: ConnectivityInfoDataSource
) : ViewModel(), SummaryViewModel {

    override val isDataSelected = stateHandle.contains(MANUFACTURER_NAME_ARG)

    override val manufacturer: String = stateHandle[MANUFACTURER_NAME_ARG] ?: ""

    override val mainType: String = stateHandle[MAIN_TYPE_NAME_ARG] ?: ""

    override val buildDate: String = stateHandle[BUILD_DATE_ARG] ?: ""

    override val connectivityNotification = MutableSharedFlow<Unit>()

    override val requestNewFilter = MutableSharedFlow<Unit>()

    override fun onNewFilterRequested() {
        viewModelScope.launch {
            if (connectivityInfoDataSource.hasConnection.value)
                requestNewFilter.emit(Unit)
            else
                connectivityNotification.emit(Unit)
        }
    }
}