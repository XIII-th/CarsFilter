package com.xiii_lab.carsfilter.remote.maintype

/**
 * Created by XIII-th on 27.04.2022
 */
data class MainTypePage(
    val page: Int,
    val totalPageCount: Int,
    val mainTypes: Map<String, String>
)
