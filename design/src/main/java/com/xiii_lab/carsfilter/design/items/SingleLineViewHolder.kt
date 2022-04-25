package com.xiii_lab.carsfilter.design.items

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiii_lab.carsfilter.design.databinding.EvenItemBinding
import com.xiii_lab.carsfilter.design.databinding.OddItemBinding

/**
 * Created by XIII-th on 25.04.2022
 */
class SingleLineViewHolder private constructor(
    private val textView: TextView
) : RecyclerView.ViewHolder(textView) {

    constructor(parent: ViewGroup, isOdd: Boolean) : this(
        LayoutInflater.from(parent.context).let { inflater ->
            if (isOdd)
                OddItemBinding.inflate(inflater, parent, false).name
            else
                EvenItemBinding.inflate(inflater, parent, false).name
        }
    )

    fun bind(title: String?) {
        textView.text = title
    }
}