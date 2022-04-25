package com.xiii_lab.carsfilter.manufacturer.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
internal class ManufacturersRepositoryImpl @Inject constructor() : ManufacturersRepository {

    override val manufacturers = MutableStateFlow(
        PagingData.from((0..10).map { Manufacturer(it.toString(), "Manufacturer $it") })
    )
}