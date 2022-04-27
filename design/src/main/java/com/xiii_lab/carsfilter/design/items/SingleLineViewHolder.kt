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
class SingleLineViewHolder<in T> private constructor(
    private val textView: TextView,
    private val onSelected: (T) -> Unit
) : RecyclerView.ViewHolder(textView) {

    private var data: T? = null

    init {
        textView.setOnClickListener {
            data?.let(onSelected)
        }
    }

    constructor(parent: ViewGroup, isOdd: Boolean, onSelected: (T) -> Unit) : this(
        LayoutInflater.from(parent.context).let { inflater ->
            if (isOdd)
                OddItemBinding.inflate(inflater, parent, false).name
            else
                EvenItemBinding.inflate(inflater, parent, false).name
        },
        onSelected
    )

    fun bind(title: String?, item: T?) {
        textView.text = title
        data = item
    }
}