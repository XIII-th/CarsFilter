package com.xiii_lab.carsfilter.remote.manufacturer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by XIII-th on 24.04.2022
 */
@Parcelize
data class Manufacturer(
    val id: String,
    val name: String
) : Parcelable
