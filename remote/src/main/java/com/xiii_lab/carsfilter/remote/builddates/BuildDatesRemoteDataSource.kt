package com.xiii_lab.carsfilter.remote.builddates

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by XIII-th on 27.04.2022
 */
interface BuildDatesRemoteDataSource {

    @GET("v1/car-types/built-dates")
    suspend fun getBuildDates(
        @Query("manufacturer") manufacturerId: String,
        @Query("main-type") mainTypeId: String,
    ): BuildDatesPage
}