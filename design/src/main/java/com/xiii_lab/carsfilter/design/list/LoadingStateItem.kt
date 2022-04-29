package com.xiii_lab.carsfilter.design.list

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.xiii_lab.carsfilter.design.databinding.ListProgressItemBinding

/**
 * Created by XIII-th on 29.04.2022
 */
class LoadingStateItem private constructor(
    private val binding: ListProgressItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    constructor(parent: ViewGroup, onRetry: () -> Unit) : this(
        ListProgressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onRetry
    )

    fun bind(state: LoadState) {
        when (state) {
            is LoadState.Error -> {
                binding.progress.visibility = GONE
                binding.retryButton.visibility = VISIBLE
            }
            LoadState.Loading -> {
                binding.progress.visibility = VISIBLE
                binding.retryButton.visibility = GONE
            }
            is LoadState.NotLoading -> {
                binding.progress.visibility = GONE
                binding.retryButton.visibility = GONE
            }
        }
    }
}