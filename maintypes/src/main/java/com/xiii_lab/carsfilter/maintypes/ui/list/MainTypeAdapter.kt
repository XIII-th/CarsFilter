package com.xiii_lab.carsfilter.maintypes.ui.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.xiii_lab.carsfilter.design.items.SingleLineViewHolder
import com.xiii_lab.carsfilter.maintypes.data.MainType

/**
 * Created by XIII-th on 27.04.2022
 */
internal class MainTypeAdapter(
    private val onSelected: (MainType) -> Unit
) :
    PagingDataAdapter<MainType, SingleLineViewHolder<MainType>>(MainTypeDiffCallback) {

    override fun onBindViewHolder(holder: SingleLineViewHolder<MainType>, position: Int) =
        getItem(position).let { mainType ->
            holder.bind(mainType?.name, mainType)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, viewType == 1, onSelected)

    override fun getItemViewType(position: Int) =
        position % 2
}