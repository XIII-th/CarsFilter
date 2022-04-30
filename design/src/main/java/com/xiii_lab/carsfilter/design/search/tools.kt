/**
 * Created by XIII-th on 28.04.2022
 */
package com.xiii_lab.carsfilter.design.search

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import com.xiii_lab.carsfilter.design.R

fun SearchViewModel.attachToMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.search_menu, menu)
    val item = menu.findItem(R.id.action_search)
    with(item.actionView as SearchView) {
        if (searchQuery.value.isNotEmpty()) {
            item.expandActionView()
            setQuery(searchQuery.value, true)
        }
        setOnQueryTextListener(SearchViewListener(::onNewSearchQuery))
        setOnCloseListener {
            onNewSearchQuery("")
            false
        }
    }
}