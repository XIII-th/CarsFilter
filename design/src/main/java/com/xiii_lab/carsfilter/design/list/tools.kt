/**
 * Created by XIII-th on 30.04.2022
 */
package com.xiii_lab.carsfilter.design.list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.xiii_lab.carsfilter.design.R
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding

fun ListFragmentBinding.updateListState(loadingState: CombinedLoadStates, itemCount: Int, query: String) {
    val state = when (loadingState.refresh) {
        LoadState.Loading -> ListState.PROGRESS
        is LoadState.NotLoading -> ListState.DATA
        is LoadState.Error -> ListState.ERROR
    }
    updateListState(state, itemCount, query)
}

fun ListFragmentBinding.updateListState(state: ListState, itemCount: Int, query: String) {
    when (state) {
        ListState.DATA -> if (itemCount == 0)
            if (query.isEmpty())
                onNoData(R.drawable.ic_baseline_filter_none_24, R.string.no_data)
            else
                onNoData(R.drawable.ic_baseline_search_24, R.string.no_data_found)
        else
            onDataLoaded()
        ListState.PROGRESS -> onProgress()
        ListState.ERROR -> if (itemCount == 0)
            if (query.isNotEmpty())
                onNoData(R.drawable.ic_baseline_search_24, R.string.no_data_found)
            else
                onNoData(R.drawable.ic_baseline_filter_none_24, R.string.no_data)
        else
            // if something loaded, let user work with it + log error (not implemented)
            onDataLoaded()
    }
}

private fun ListFragmentBinding.onNoData(
    @DrawableRes iconRes: Int,
    @StringRes commentRes: Int
) {
    progress.visibility = GONE
    list.visibility = GONE
    placeholder.apply {
        root.visibility = VISIBLE
        placeholderImage.setImageResource(iconRes)
        placeholderComment.setText(commentRes)
    }
}

private fun ListFragmentBinding.onDataLoaded() {
    progress.visibility = GONE
    list.visibility = VISIBLE
    placeholder.root.visibility = GONE
}

private fun ListFragmentBinding.onProgress() {
    progress.visibility = VISIBLE
    list.visibility = GONE
    placeholder.root.visibility = GONE
}

