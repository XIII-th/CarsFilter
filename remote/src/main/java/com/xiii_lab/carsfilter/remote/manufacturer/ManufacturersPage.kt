package com.xiii_lab.carsfilter.remote.manufacturer

import com.squareup.moshi.Json
import com.xiii_lab.carsfilter.remote.BuildConfig

/**
 * Created by XIII-th on 25.04.2022
 */
class ManufacturersPage(
    val page: Int,
    val totalPageCount: Int,
    @field:Json(name = BuildConfig.JSON_CONTENT_FIELD)
    val manufacturers: Map<String, String>
)
