package com.xiii_lab.carsfilter.environment.connectivity

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by XIII-th on 30.04.2022
 */
internal class ConnectivityInfoDataSourceImpl :
    ConnectivityManager.NetworkCallback(),
    ConnectivityInfoDataSource {

    override val hasConnection = MutableStateFlow(false)

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        hasConnection.value = true
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        hasConnection.value = false
    }

    override fun onUnavailable() {
        super.onUnavailable()
        hasConnection.value = false
    }
}