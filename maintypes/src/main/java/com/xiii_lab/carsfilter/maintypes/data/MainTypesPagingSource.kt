package com.xiii_lab.carsfilter.maintypes.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xiii_lab.carsfilter.remote.maintype.MainType
import com.xiii_lab.carsfilter.remote.maintype.MainTypesRemoteDataSource

/**
 * Created by XIII-th on 27.04.2022
 */
internal class MainTypesPagingSource(
    private val manufacturerId: String,
    private val mainTypesRemoteDataSource: MainTypesRemoteDataSource
) : PagingSource<Int, MainType>() {

    override fun getRefreshKey(state: PagingState<Int, MainType>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainType> {
        return try {
            val response = mainTypesRemoteDataSource.getMainTypes(manufacturerId, params.key ?: 0)
            LoadResult.Page(
                data = response.mainTypes.entries.map { (id, name) ->
                    MainType(id, name)
                },
                prevKey = if (response.page == 0) null else response.page - 1,
                nextKey = if (response.page == response.totalPageCount) null else response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}