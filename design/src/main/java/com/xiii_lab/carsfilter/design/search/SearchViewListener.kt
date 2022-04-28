package com.xiii_lab.carsfilter.design.search

import androidx.appcompat.widget.SearchView

/**
 * Created by XIII-th on 28.04.2022
 */
internal class SearchViewListener(
    private val onQueryChanged: (String) -> Unit
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryChanged(query.orEmpty())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onQueryChanged(newText.orEmpty())
        return false
    }
}