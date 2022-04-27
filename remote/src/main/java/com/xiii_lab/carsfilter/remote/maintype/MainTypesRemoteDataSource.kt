package com.xiii_lab.carsfilter.remote.maintype

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by XIII-th on 27.04.2022
 */
interface MainTypesRemoteDataSource {

    // TODO: Check paging support
    @GET("v1/car-types/main-types")
    suspend fun getMainTypes(
        @Query("manufacturer") manufacturerId: String,
        @Query("page") page: Int
    ): MainTypePage
}