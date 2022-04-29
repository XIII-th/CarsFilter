package com.xiii_lab.carsfilter.environment.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSource
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by XIII-th on 30.04.2022
 */
@Module
@InstallIn(SingletonComponent::class)
internal class EnvironmentModule {

    @Provides
    fun provideConnectivityInfoDataSource(@ApplicationContext appContext: Context): ConnectivityInfoDataSource {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val callback = ConnectivityInfoDataSourceImpl()
        val manager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.registerNetworkCallback(networkRequest, callback)
        return callback
    }
}