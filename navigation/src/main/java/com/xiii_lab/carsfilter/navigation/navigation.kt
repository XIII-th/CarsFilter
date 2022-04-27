/**
 * Created by XIII-th on 27.04.2022
 */
package com.xiii_lab.carsfilter.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController

fun NavController.openManufacturersSelection() =
    navigate(R.id.action_select_manufacturer)

const val MANUFACTURER_ARG = "MANUFACTURER_ARG"
fun NavController.openMainTypeSelection(manufacturer: Parcelable) =
    navigate(R.id.action_select_main_type, Bundle().apply {
        putParcelable(MANUFACTURER_ARG, manufacturer)
    })

const val MAIN_TYPE_ARG = "MAIN_TYPE_ARG"
fun NavController.openBuildDateSelection(manufacturer: Parcelable, mainType: Parcelable) =
    navigate(R.id.action_select_build_date, Bundle().apply {
        putParcelable(MANUFACTURER_ARG, manufacturer)
        putParcelable(MAIN_TYPE_ARG, mainType)
    })

const val MANUFACTURER_NAME_ARG = "MANUFACTURER_NAME_ARG"
const val MAIN_TYPE_NAME_ARG = "MAIN_TYPE_NAME_ARG"
const val BUILD_DATE_ARG = "BUILD_DATE_ARG"
fun NavController.openSummary(manufacturerName: String, mainTypeName: String, buildDate: String) =
    navigate(R.id.action_show_summary, Bundle().apply {
        putString(MANUFACTURER_NAME_ARG, manufacturerName)
        putString(MAIN_TYPE_NAME_ARG, mainTypeName)
        putString(BUILD_DATE_ARG, buildDate)
    })