package com.xiii_lab.carsfilter.design.list

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * Created by XIII-th on 29.04.2022
 */
class LoadingStateAdapter(
    private val onRetry: () -> Unit
): LoadStateAdapter<LoadingStateItem>() {

    override fun onBindViewHolder(holder: LoadingStateItem, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingStateItem(parent, onRetry)
}