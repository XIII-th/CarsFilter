/**
 * Created by XIII-th on 27.04.2022
 */
package com.xiii_lab.carsfilter.navigation

import android.os.Bundle
import androidx.navigation.NavController

fun NavController.openManufacturersSelection() =
    navigate(R.id.action_select_manufacturer)

const val MANUFACTURER_ID_ARG = "MANUFACTURER_ID_ARG"
fun NavController.openMainTypeSelection(manufacturerId: String) =
    navigate(R.id.action_select_main_type, Bundle().apply {
        putString(MANUFACTURER_ID_ARG, manufacturerId)
    })

const val MAIN_TYPE_ID_ARG = "MAIN_TYPE_ID_ARG"
fun NavController.openBuildDateSelection(manufacturerId: String, mainTypeId: String) =
    navigate(R.id.action_select_build_date, Bundle().apply {
        putString(MANUFACTURER_ID_ARG, manufacturerId)
        putString(MAIN_TYPE_ID_ARG, mainTypeId)
    })

const val BUILD_DATE_ID_ARG = "BUILD_DATE_ID_ARG"
fun NavController.openSummary(manufacturerId: String, mainTypeId: String, buildDateId: String) =
    navigate(R.id.action_show_summary, Bundle().apply {
        putString(MANUFACTURER_ID_ARG, manufacturerId)
        putString(MAIN_TYPE_ID_ARG, mainTypeId)
        putString(BUILD_DATE_ID_ARG, buildDateId)
    })