package com.xiii_lab.carsfilter.builddtates.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.xiii_lab.carsfilter.remote.builddates.BuildDate
import com.xiii_lab.carsfilter.design.items.SingleLineViewHolder

/**
 * Created by XIII-th on 27.04.2022
 */
internal class BuildDatesAdapter(
    private val onSelected: (BuildDate) -> Unit
) :
    ListAdapter<BuildDate, SingleLineViewHolder<BuildDate>>(BuildDateDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SingleLineViewHolder(parent, onSelected)

    override fun onBindViewHolder(holder: SingleLineViewHolder<BuildDate>, position: Int) =
        getItem(position).let { buildDate ->
            holder.bind(buildDate.date, position % 2 == 1, buildDate)
        }
}