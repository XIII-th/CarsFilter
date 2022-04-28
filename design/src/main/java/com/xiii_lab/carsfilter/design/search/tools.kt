/**
 * Created by XIII-th on 28.04.2022
 */
package com.xiii_lab.carsfilter.design.search

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import com.xiii_lab.carsfilter.design.R

fun createSearchMenu(menu: Menu, inflater: MenuInflater, onQueryChanged: (String) -> Unit) {
    inflater.inflate(R.menu.search_menu, menu)
    with(menu.findItem(R.id.action_search).actionView as SearchView) {
        setOnQueryTextListener(SearchViewListener(onQueryChanged))
        setOnCloseListener {
            onQueryChanged("")
            false
        }
    }
}