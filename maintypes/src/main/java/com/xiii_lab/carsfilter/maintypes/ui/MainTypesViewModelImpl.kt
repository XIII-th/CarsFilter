package com.xiii_lab.carsfilter.maintypes.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.xiii_lab.carsfilter.maintypes.data.MainTypesRepository
import com.xiii_lab.carsfilter.navigation.MANUFACTURER_ARG
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
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

    private val queryFlow = MutableStateFlow("")

    override val toolbarTitle = manufacturer.name

    override val mainTypes = queryFlow.flatMapLatest { query ->
        mainTypesRepository.getMainTypes(manufacturer.id)
            .map { pagingData ->
                if (query.isEmpty())
                    pagingData
                else
                    pagingData.filter { mainType ->
                        mainType.name.contains(query, true)
                    }
            }
    }.cachedIn(viewModelScope)

    override val selectedMainType = MutableSharedFlow<Pair<Manufacturer, MainType>>()

    override fun onNewSearchQuery(query: String) {
        viewModelScope.launch {
            queryFlow.emit(query.trim())
        }
    }

    override fun onSelected(mainType: MainType) {
        viewModelScope.launch {
            selectedMainType.emit(manufacturer to mainType)
        }
    }
}