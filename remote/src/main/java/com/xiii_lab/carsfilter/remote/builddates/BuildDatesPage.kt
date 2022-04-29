package com.xiii_lab.carsfilter.remote.builddates

import com.squareup.moshi.Json
import com.xiii_lab.carsfilter.remote.BuildConfig

/**
 * Created by XIII-th on 27.04.2022
 */
data class BuildDatesPage(
    @field:Json(name = BuildConfig.JSON_CONTENT_FIELD)
    val buildDates: Map<String, String>
)
