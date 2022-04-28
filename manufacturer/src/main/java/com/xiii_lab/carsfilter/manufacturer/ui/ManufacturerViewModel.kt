package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.paging.PagingData
import com.xiii_lab.carsfilter.design.search.SearchViewModel
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface ManufacturerViewModel : SearchViewModel {

    val manufacturers: Flow<PagingData<Manufacturer>>

    val selectedManufacturer: Flow<Manufacturer>

    fun onSelected(manufacturer: Manufacturer)
}