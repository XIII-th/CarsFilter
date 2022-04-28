package com.xiii_lab.carsfilter.maintypes.ui

import androidx.paging.PagingData
import com.xiii_lab.carsfilter.design.search.SearchViewModel
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface MainTypesViewModel : SearchViewModel {

    val toolbarTitle: String

    val mainTypes: Flow<PagingData<MainType>>

    // TODO: Documentation
    val selectedMainType: Flow<Pair<Manufacturer, MainType>>

    fun onSelected(mainType: MainType)
}