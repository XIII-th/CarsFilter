package com.xiii_lab.carsfilter.maintypes.ui

import androidx.paging.PagingData
import com.xiii_lab.carsfilter.maintypes.data.MainType
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface MainTypesViewModel {

    val mainTypes: Flow<PagingData<MainType>>

    // TODO: Documentation
    val selectedMainType: Flow<Pair<String, String>>

    fun onNewSearchQuery(string: String)

    fun onSelected(mainType: MainType)
}