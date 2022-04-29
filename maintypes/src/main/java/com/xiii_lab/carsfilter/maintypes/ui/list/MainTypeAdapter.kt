package com.xiii_lab.carsfilter.maintypes.ui.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.xiii_lab.carsfilter.design.list.SingleLineViewHolder
import com.xiii_lab.carsfilter.remote.maintype.MainType

/**
 * Created by XIII-th on 27.04.2022
 */
internal class MainTypeAdapter(
    private val onSelected: (MainType) -> Unit
) :
    PagingDataAdapter<MainType, SingleLineViewHolder<MainType>>(MainTypeDiffCallback) {

    override fun onBindViewHolder(holder: SingleLineViewHolder<MainType>, position: Int) =
        getItem(position).let { mainType ->
            holder.bind(mainType?.name, position % 2 == 1, mainType)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, onSelected)
}