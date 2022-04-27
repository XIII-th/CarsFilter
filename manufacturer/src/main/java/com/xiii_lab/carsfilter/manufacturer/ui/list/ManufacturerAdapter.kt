package com.xiii_lab.carsfilter.manufacturer.ui.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.xiii_lab.carsfilter.design.items.SingleLineViewHolder
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
            holder.bind(manufacturer?.name, manufacturer)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, viewType == 1, onSelected)

    override fun getItemViewType(position: Int) =
        position % 2
}