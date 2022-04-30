package com.xiii_lab.carsfilter.design.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.xiii_lab.carsfilter.design.R

/**
 * Created by XIII-th on 30.04.2022
 */
sealed class ListState {

    object Data : ListState()

    object Progress : ListState()

    data class Placeholder(
        @DrawableRes val iconRes: Int,
        @StringRes val commentRes: Int
    ) : ListState() {

        companion object {

            val NoData = Placeholder(R.drawable.ic_baseline_filter_none_24, R.string.no_data)

            val DataNotFound = Placeholder(R.drawable.ic_baseline_search_24, R.string.no_data_found)

            val NoConnection = Placeholder(
                R.drawable.ic_baseline_signal_wifi_connected_no_internet_4_24,
                R.string.no_connection
            )

            val CommonError = Placeholder(R.drawable.ic_baseline_error_24, R.string.common_error)
        }
    }
}
