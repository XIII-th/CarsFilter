package com.xiii_lab.carsfilter.maintypes.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.xiii_lab.carsfilter.maintypes.data.MainType

/**
 * Created by XIII-th on 27.04.2022
 */
internal object MainTypeDiffCallback : DiffUtil.ItemCallback<MainType>() {

    override fun areItemsTheSame(oldItem: MainType, newItem: MainType) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MainType, newItem: MainType) =
        oldItem == newItem
}