package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.filter
import com.xiii_lab.carsfilter.design.list.ListState
import com.xiii_lab.carsfilter.design.list.ListStateViewModel
import com.xiii_lab.carsfilter.design.list.ListStateViewModelImpl
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
internal class ManufacturerViewModelImpl private constructor(
    repository: ManufacturersRepository,
    private val connectivityInfoDataSource: ConnectivityInfoDataSource,
    searchDelegate: SearchViewModelDelegate,
    private val stateDelegate: ListStateViewModelImpl
) : ViewModel(),
    ManufacturerViewModel,
    SearchViewModel by searchDelegate,
    ListStateViewModel by stateDelegate {

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
                if (!hasConnection) {
                    searchDelegate.setSearchEnabled(false)
                    stateDelegate.onNoData(ListState.Placeholder.NoConnection)
                } else {
                    searchDelegate.setSearchEnabled(true)
                    reload.emit(Unit)
                }
            }
        }
    }

    @Inject
    constructor(
        repository: ManufacturersRepository,
        connectivityInfoDataSource: ConnectivityInfoDataSource
    ) : this(
        repository,
        connectivityInfoDataSource,
        SearchViewModelDelegate(),
        ListStateViewModelImpl()
    )

    override fun onLoadStateUpdated(loadState: CombinedLoadStates, itemCount: Int) {
        when (loadState.refresh) {
            is LoadState.NotLoading -> if (itemCount == 0)
                if (searchQuery.value.isEmpty())
                    stateDelegate.onNoData(ListState.Placeholder.NoData)
                else
                    stateDelegate.onNoData(ListState.Placeholder.DataNotFound)
            else
                stateDelegate.onDataLoaded()
            LoadState.Loading -> stateDelegate.onProgress()
            is LoadState.Error -> if (connectivityInfoDataSource.hasConnection.value)
                stateDelegate.onNoData(ListState.Placeholder.CommonError)
            else
                stateDelegate.onNoData(ListState.Placeholder.NoConnection)
        }
    }

    override fun onSelected(manufacturer: Manufacturer) {
        viewModelScope.launch {
            selectedManufacturer.emit(manufacturer)
        }
    }
}