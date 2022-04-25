package com.xiii_lab.carsfilter.remote.manufacturer

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by XIII-th on 25.04.2022
 */
interface ManufacturersRemoteDataSource {

    @GET("v1/car-types/manufacturer?pageSize=$PAGE_SIZE")
    suspend fun getManufacturers(@Query("page") page: Int): ManufacturersPage

    companion object {
        const val PAGE_SIZE = 15
    }
}