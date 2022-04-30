package com.xiii_lab.carsfilter.builddtates.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiii_lab.carsfilter.builddtates.data.BuildDatesRepository
import com.xiii_lab.carsfilter.design.list.ListState
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSource
import com.xiii_lab.carsfilter.navigation.MAIN_TYPE_ARG
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_ARG
import com.xiii_lab.carsfilter.remote.builddates.BuildDate
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 27.04.2022
 */
@HiltViewModel
internal class BuildDateViewModelImpl @Inject constructor(
    stateHandle: SavedStateHandle,
    connectivityInfoDataSource: ConnectivityInfoDataSource,
    buildDatesRepository: BuildDatesRepository
) : ViewModel(), BuildDateViewModel {

    // TODO: Handle absences of id
    private val manufacturer: Manufacturer = stateHandle[MANUFACTURER_ARG]!!
    private val mainType: MainType = stateHandle[MAIN_TYPE_ARG]!!

    override val toolbarTitle = manufacturer.name

    override val toolbarSubtitle = mainType.name

    override val buildDates =
        connectivityInfoDataSource.hasConnection.flatMapLatest { hasConnection ->
            val flow = if (hasConnection) {
                buildDatesRepository.getBuildDates(manufacturer.id, mainType.id)
            } else
                emptyFlow()
            flow.onStart {
                ListState.PROGRESS to emptyList<BuildDate>()
            }
        }.map { buildDates ->
            ListState.DATA to buildDates
        }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    override val selectedBuildDate = MutableSharedFlow<Triple<Manufacturer, MainType, BuildDate>>()

    override fun onSelected(buildDate: BuildDate) {
        viewModelScope.launch {
            selectedBuildDate.emit(Triple(manufacturer, mainType, buildDate))
        }
    }
}