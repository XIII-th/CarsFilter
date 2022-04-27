package com.xiii_lab.carsfilter.remote.builddates

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by XIII-th on 27.04.2022
 */
@Parcelize
data class BuildDate(
    val id: String,
    val date: String
) : Parcelable
