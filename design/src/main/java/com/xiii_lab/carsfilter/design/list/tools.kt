/**
 * Created by XIII-th on 30.04.2022
 */
package com.xiii_lab.carsfilter.design.list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding

fun ListFragmentBinding.updateListState(state: CombinedLoadStates, itemCount: Int, query: String) {
    when (state.refresh) {
        is LoadState.NotLoading -> if (itemCount == 0)
            if (query.isEmpty())
                onNoData(ListState.Placeholder.NoData)
            else
                onNoData(ListState.Placeholder.DataNotFound)
        else
            onDataLoaded()
        LoadState.Loading -> onProgress()
        is LoadState.Error -> if (itemCount == 0)
            if (query.isNotEmpty())
                onNoData(ListState.Placeholder.DataNotFound)
            else
                onNoData(ListState.Placeholder.NoData)
        else
            // if something loaded, let user work with it + log error
            onDataLoaded()
    }
}

private fun ListFragmentBinding.onNoData(state: ListState.Placeholder) {
    progress.visibility = GONE
    list.visibility = GONE
    placeholder.apply {
        root.visibility = VISIBLE
        placeholderImage.setImageResource(state.iconRes)
        placeholderComment.setText(state.commentRes)
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

