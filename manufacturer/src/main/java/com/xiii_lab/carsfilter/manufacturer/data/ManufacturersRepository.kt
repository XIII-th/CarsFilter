package com.xiii_lab.carsfilter.manufacturer.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by XIII-th on 24.04.2022
 */
internal interface ManufacturersRepository {

    val manufacturers: StateFlow<PagingData<Manufacturer>>
}