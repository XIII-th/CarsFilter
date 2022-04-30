/**
 * Created by XIII-th on 28.04.2022
 */
package com.xiii_lab.carsfilter.design.search

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.SearchView
import com.xiii_lab.carsfilter.design.R

fun SearchViewModel.attachToMenu(menu: Menu, inflater: MenuInflater): MenuItem {
    inflater.inflate(R.menu.search_menu, menu)
    val item = menu.findItem(R.id.action_search)
    with(item.actionView as SearchView) {
        visibility = if (searchEnabled.value) VISIBLE else GONE
        if (searchEnabled.value && searchQuery.value.isNotEmpty()) {
            item.expandActionView()
            setQuery(searchQuery.value, true)
        }
        setOnQueryTextListener(SearchViewListener(::onNewSearchQuery))
        setOnCloseListener {
            onNewSearchQuery("")
            false
        }
    }
    return item
}