package com.xiii_lab.carsfilter.remote.maintype

import com.xiii_lab.carsfilter.remote.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by XIII-th on 27.04.2022
 */
interface MainTypesRemoteDataSource {

    // backend supports paging for main types, so data source supports as well
    @GET("v1/car-types/main-types?pageSize=$PAGE_SIZE")
    suspend fun getMainTypes(
        @Query("manufacturer") manufacturerId: String,
        @Query("page") page: Int
    ): MainTypePage
}