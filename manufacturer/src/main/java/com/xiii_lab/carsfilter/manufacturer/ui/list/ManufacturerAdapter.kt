package com.xiii_lab.carsfilter.manufacturer.ui.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.xiii_lab.carsfilter.design.items.SingleLineViewHolder
import com.xiii_lab.carsfilter.manufacturer.data.Manufacturer

/**
 * Created by XIII-th on 25.04.2022
 */
internal class ManufacturerAdapter :
    PagingDataAdapter<Manufacturer, SingleLineViewHolder>(ManufacturerDiffCallback) {

    override fun onBindViewHolder(holder: SingleLineViewHolder, position: Int) =
        getItem(position).let { manufacturer ->
            holder.bind(manufacturer?.name)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, viewType == 1)

    override fun getItemViewType(position: Int) =
        position % 2
}