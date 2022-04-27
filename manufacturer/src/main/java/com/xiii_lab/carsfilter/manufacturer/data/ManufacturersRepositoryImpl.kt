package com.xiii_lab.carsfilter.manufacturer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import com.xiii_lab.carsfilter.remote.manufacturer.ManufacturersRemoteDataSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
internal class ManufacturersRepositoryImpl @Inject constructor(
    private val pagingSource: ManufacturerPagingSource
) : ManufacturersRepository {

    override val manufacturers: Flow<PagingData<Manufacturer>> = Pager(PagingConfig(PAGE_SIZE)) {
        pagingSource
    }.flow
        // TODO: .cachedIn()
}