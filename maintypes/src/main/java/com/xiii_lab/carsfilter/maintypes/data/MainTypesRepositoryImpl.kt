package com.xiii_lab.carsfilter.maintypes.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xiii_lab.carsfilter.remote.manufacturer.ManufacturersRemoteDataSource

internal fun interface TypesPagingSourceFactory {

    fun create(manufacturerId: String): MainTypesPagingSource
}

/**
 * Created by XIII-th on 27.04.2022
 */
internal class MainTypesRepositoryImpl(
    private val createPagingSourceFactory: TypesPagingSourceFactory
) : MainTypesRepository {

    override fun getMainTypes(manufacturerId: String) = Pager(
        PagingConfig(
            // TODO: Check paging support
            ManufacturersRemoteDataSource.PAGE_SIZE
        )
    ) {
        createPagingSourceFactory.create(manufacturerId)
    }.flow
    // TODO: .cachedIn()
}