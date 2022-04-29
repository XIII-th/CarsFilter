package com.xiii_lab.carsfilter.design.list

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.xiii_lab.carsfilter.design.databinding.ListProgressItemBinding
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

/**
 * Created by XIII-th on 29.04.2022
 */
class LoadingStateItem private constructor(
    private val binding: ListProgressItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var showProgressJob: Job? = null

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    constructor(parent: ViewGroup, onRetry: () -> Unit) : this(
        ListProgressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onRetry
    )

    fun bind(state: LoadState) = with(binding) {
        when (state) {
            is LoadState.Error -> {
                hideProgress()
                retryButton.visibility = VISIBLE
            }
            LoadState.Loading -> {
                showProgress()
                retryButton.visibility = GONE
            }
            is LoadState.NotLoading -> {
                hideProgress()
                retryButton.visibility = GONE
            }
        }
    }

    internal fun recycle() {
        coroutineScope.cancel("Loading item recycled")
    }

    private fun showProgress() {
        if (showProgressJob == null) {
            showProgressJob = coroutineScope.launch {
                // progress should be visible only if request takes really long time
                delay(1.seconds)
                binding.progress.visibility = VISIBLE
            }
        }
    }

    private fun hideProgress() {
        showProgressJob?.cancel("Progress was hidden")
        showProgressJob = null
        binding.progress.visibility = GONE
    }
}