package com.xiii_lab.carsfilter.maintypes.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Created by XIII-th on 27.04.2022
 */
internal interface MainTypesRepository {

    fun getMainTypes(manufacturerId: String): Flow<PagingData<MainType>>
}