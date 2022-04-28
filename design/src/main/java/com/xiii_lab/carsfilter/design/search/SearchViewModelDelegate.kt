package com.xiii_lab.carsfilter.design.search

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by XIII-th on 28.04.2022
 */
class SearchViewModelDelegate: SearchViewModel {

    override val searchQuery = MutableStateFlow("")

    override fun onNewSearchQuery(query: String) {
        searchQuery.value = query.trim()
    }
}