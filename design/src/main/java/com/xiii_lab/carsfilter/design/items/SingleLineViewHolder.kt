package com.xiii_lab.carsfilter.design.items

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.R
import com.xiii_lab.carsfilter.design.databinding.ListItemBinding

/**
 * Created by XIII-th on 25.04.2022
 * TODO Documentation
 */
class SingleLineViewHolder<in T> private constructor(
    private val textView: TextView,
    private val onSelected: (T) -> Unit
) : RecyclerView.ViewHolder(textView) {

    private val defaultBackground = textView.background
    private val defaultTextColor = textView.textColors

    private val oddBackground: Drawable
    private val oddTextColor: ColorStateList

    private var data: T? = null

    init {
        textView.setOnClickListener {
            data?.let(onSelected)
        }
        with(textView.context.theme) {
            val resolvedAttr = TypedValue()
            oddBackground = if (resolveAttribute(R.attr.colorPrimarySurface, resolvedAttr, true))
                ColorDrawable(resolvedAttr.data).apply { alpha = 127 }
            else
                defaultBackground

            oddTextColor = if (resolveAttribute(R.attr.colorOnPrimarySurface, resolvedAttr, true))
                ColorStateList.valueOf(resolvedAttr.data)
            else
                defaultTextColor
        }
    }

    constructor(parent: ViewGroup, onSelected: (T) -> Unit) : this(
        LayoutInflater.from(parent.context).let { inflater ->
            ListItemBinding.inflate(inflater, parent, false).name
        },
        onSelected
    )

    fun bind(title: String?, isOdd: Boolean, item: T?) {
        textView.text = title
        data = item
        if (isOdd) {
            textView.background = oddBackground
            textView.setTextColor(oddTextColor)
        } else {
            textView.background = defaultBackground
            textView.setTextColor(defaultTextColor)
        }
    }
}