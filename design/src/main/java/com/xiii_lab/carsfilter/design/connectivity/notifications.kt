/**
 * Created by XIII-th on 30.04.2022
 */
package com.xiii_lab.carsfilter.design.connectivity

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.xiii_lab.carsfilter.design.R

fun View.showNoConnectionNotification() {
    Snackbar.make(this, R.string.no_connection_notification, Snackbar.LENGTH_INDEFINITE)
        .setAction(R.string.ok_button) { /* Do nothing. Just close the notification */ }
        .show()
}

fun View.showLostConnectionNotification() {
    Snackbar.make(this, R.string.lost_connection, Snackbar.LENGTH_LONG).show()
}
