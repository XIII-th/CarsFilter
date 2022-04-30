package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.xiii_lab.carsfilter.design.search.SearchViewModel
import com.xiii_lab.carsfilter.design.search.SearchViewModelDelegate
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSource
import com.xiii_lab.carsfilter.manufacturer.data.ManufacturersRepository
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
@HiltViewModel
internal class ManufacturerViewModelImpl @Inject constructor(
    repository: ManufacturersRepository,
    private val connectivityInfoDataSource: ConnectivityInfoDataSource
) : ViewModel(),
    ManufacturerViewModel,
    SearchViewModel by SearchViewModelDelegate() {

    override val manufacturers = searchQuery.flatMapLatest { query ->
        print(query)
        repository.manufacturers.map { pagingData ->
            if (query.isEmpty())
                pagingData
            else
                pagingData.filter { manufacturer ->
                    manufacturer.name.contains(query, true)
                }
        }
    }.cachedIn(viewModelScope)

    override val selectedManufacturer = MutableSharedFlow<Manufacturer>()

    override val reload = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch {
            connectivityInfoDataSource.hasConnection.collect { hasConnection ->
                if (hasConnection)
                    reload.emit(Unit)
            }
        }
    }

    override fun onSelected(manufacturer: Manufacturer) {
        viewModelScope.launch {
            selectedManufacturer.emit(manufacturer)
        }
    }
}