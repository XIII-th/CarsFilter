package com.xiii_lab.carsfilter.remote.maintype

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by XIII-th on 27.04.2022
 */
@Parcelize
data class MainType(
    val id: String,
    val name: String
) : Parcelable
