package com.xiii_lab.carsfilter.manufacturer.ui.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.xiii_lab.carsfilter.design.list.SingleLineViewHolder
import com.xiii_lab.carsfilter.remote.manufacturer.Manufacturer

/**
 * Created by XIII-th on 25.04.2022
 */
internal class ManufacturerAdapter(
    private val onSelected: (Manufacturer) -> Unit
) :
    PagingDataAdapter<Manufacturer, SingleLineViewHolder<Manufacturer>>(ManufacturerDiffCallback) {

    override fun onBindViewHolder(holder: SingleLineViewHolder<Manufacturer>, position: Int) =
        getItem(position).let { manufacturer ->
            holder.bind(manufacturer?.name, position % 2 == 1, manufacturer)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, onSelected)
}