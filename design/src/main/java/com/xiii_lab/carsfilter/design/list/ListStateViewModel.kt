package com.xiii_lab.carsfilter.design.list

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by XIII-th on 30.04.2022
 */
interface ListStateViewModel {

    val listState: StateFlow<ListState>
}