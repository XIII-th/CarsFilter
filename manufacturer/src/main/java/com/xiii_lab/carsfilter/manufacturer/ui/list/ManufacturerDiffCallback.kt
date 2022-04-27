package com.xiii_lab.carsfilter.manufacturer.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer

/**
 * Created by XIII-th on 25.04.2022
 */
internal object ManufacturerDiffCallback : DiffUtil.ItemCallback<Manufacturer>() {

    override fun areItemsTheSame(oldItem: Manufacturer, newItem: Manufacturer) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Manufacturer, newItem: Manufacturer) =
        oldItem == newItem
}