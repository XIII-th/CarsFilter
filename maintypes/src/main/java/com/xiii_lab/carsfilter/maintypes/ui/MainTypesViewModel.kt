package com.xiii_lab.carsfilter.maintypes.ui

import androidx.paging.PagingData
import com.xiii_lab.carsfilter.maintypes.data.MainType
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface MainTypesViewModel {

    val mainTypes: Flow<PagingData<MainType>>

    val selectedMainType: Flow<MainType>

    fun onNewSearchQuery(string: String)

    fun onSelected(mainType: MainType)
}