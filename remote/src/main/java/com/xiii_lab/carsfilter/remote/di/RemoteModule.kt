package com.xiii_lab.carsfilter.remote.di

import com.xiii_lab.carsfilter.remote.BuildConfig
import com.xiii_lab.carsfilter.remote.maintype.MainTypesRemoteDataSource
import com.xiii_lab.carsfilter.remote.manufacturer.ManufacturersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by XIII-th on 25.04.2022
 */
@Module
@InstallIn(SingletonComponent::class)
internal class RemoteModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(BuildConfig.API_KEY, BuildConfig.API_KEY_VALUE)
                .build()

            val request = original.newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideManufacturersDataSource(retrofit: Retrofit) =
        retrofit.create(ManufacturersRemoteDataSource::class.java)

    @Provides
    fun provideMainTypesDataSource(retrofit: Retrofit) =
        retrofit.create(MainTypesRemoteDataSource::class.java)
}