package com.xiii_lab.carsfilter.builddtates.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiii_lab.carsfilter.builddtates.data.BuildDate
import com.xiii_lab.carsfilter.builddtates.data.BuildDatesRepository
import com.xiii_lab.carsfilter.navigation.MAIN_TYPE_ID_ARG
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 27.04.2022
 */
@HiltViewModel
internal class BuildDateViewModelImpl @Inject constructor(
    stateHandle: SavedStateHandle,
    buildDatesRepository: BuildDatesRepository
) : ViewModel(), BuildDateViewModel {

    // TODO: Handle absences of id
    private val manufacturerId: String = stateHandle[MANUFACTURER_ID_ARG]!!
    private val mainTypeId: String = stateHandle[MAIN_TYPE_ID_ARG]!!

    override val buildDates = buildDatesRepository.getBuildDates(manufacturerId, mainTypeId)

    override val selectedBuildDate = MutableSharedFlow<Triple<String, String, String>>()

    override fun onSelected(buildDate: BuildDate) {
        viewModelScope.launch {
            selectedBuildDate.emit(Triple(manufacturerId, mainTypeId, buildDate.id))
        }
    }
}