package com.xiii_lab.carsfilter.manufacturer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer
import com.xiii_lab.carsfilter.remote.manufacturer.ManufacturersRemoteDataSource
import javax.inject.Inject

/**
 * Created by XIII-th on 25.04.2022
 */
internal class ManufacturerPagingSource @Inject constructor(
    private val manufacturersRemoteDataSource: ManufacturersRemoteDataSource
) : PagingSource<Int, Manufacturer>() {

    override fun getRefreshKey(state: PagingState<Int, Manufacturer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manufacturer> {
        return try {
            val response = manufacturersRemoteDataSource.getManufacturers(params.key ?: 0)
            LoadResult.Page(
                data = response.manufacturers.entries.map { (id, name) ->
                    Manufacturer(id, name)
                },
                prevKey = if (response.page == 0) null else response.page - 1,
                nextKey = if (response.page == response.totalPageCount) null else response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}