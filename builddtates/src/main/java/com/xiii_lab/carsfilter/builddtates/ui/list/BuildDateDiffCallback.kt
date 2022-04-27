package com.xiii_lab.carsfilter.builddtates.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.xiii_lab.carsfilter.remote.builddates.BuildDate

/**
 * Created by XIII-th on 27.04.2022
 */
internal object BuildDateDiffCallback : DiffUtil.ItemCallback<BuildDate>() {

    override fun areItemsTheSame(oldItem: BuildDate, newItem: BuildDate) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BuildDate, newItem: BuildDate) =
        oldItem == newItem
}