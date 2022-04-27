package com.xiii_lab.carsfilter.maintypes.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiii_lab.carsfilter.maintypes.data.MainTypesRepository
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_ARG
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 27.04.2022
 */
@HiltViewModel
internal class MainTypesViewModelImpl @Inject constructor(
    stateHandle: SavedStateHandle,
    mainTypesRepository: MainTypesRepository
) : ViewModel(), MainTypesViewModel {

    // TODO: Handle absences of id
    private val manufacturer: Manufacturer = stateHandle[MANUFACTURER_ARG]!!

    override val mainTypes = mainTypesRepository.getMainTypes(manufacturer.id)

    override val selectedMainType = MutableSharedFlow<Pair<Manufacturer, MainType>>()

    override fun onNewSearchQuery(string: String) {
        TODO("Not yet implemented")
    }

    override fun onSelected(mainType: MainType) {
        viewModelScope.launch {
            selectedMainType.emit(manufacturer to mainType)
        }
    }
}