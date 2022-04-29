package com.xiii_lab.carsfilter.environment.connectivity

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by XIII-th on 30.04.2022
 */
interface ConnectivityInfoDataSource {

    /**
     * Provides internet connection state
     */
    val hasConnection: StateFlow<Boolean>
}