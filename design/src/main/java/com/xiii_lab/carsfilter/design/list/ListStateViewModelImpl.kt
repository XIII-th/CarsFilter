package com.xiii_lab.carsfilter.design.list

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by XIII-th on 30.04.2022
 */
class ListStateViewModelImpl : ListStateViewModel {

    private var stored: ListState = ListState.Progress

    override val listState = MutableStateFlow(stored)

    fun onDataLoaded() {
        stored = ListState.Data
        listState.value = ListState.Data
    }

    fun onProgress() {
        stored = ListState.Progress
        listState.value = ListState.Progress
    }

    fun onNoData(placeholder: ListState.Placeholder) {
        stored = listState.value
        listState.value = placeholder
    }
}