package com.xiii_lab.carsfilter.design.search

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by XIII-th on 28.04.2022
 */
interface SearchViewModel {

    val searchQuery: StateFlow<String>

    fun onNewSearchQuery(query: String)
}