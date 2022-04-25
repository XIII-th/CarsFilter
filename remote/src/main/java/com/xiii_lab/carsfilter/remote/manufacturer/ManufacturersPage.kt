package com.xiii_lab.carsfilter.remote.manufacturer

/**
 * Created by XIII-th on 25.04.2022
 */
class ManufacturersPage(
    val page: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val manufacturers: List<Manufacturer>
)
