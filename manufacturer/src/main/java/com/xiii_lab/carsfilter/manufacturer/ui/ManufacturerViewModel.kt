package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.lifecycle.ViewModel
import androidx.paging.filter
import com.xiii_lab.carsfilter.manufacturer.data.ManufacturersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
@HiltViewModel
internal class ManufacturerViewModel @Inject constructor(
    repository: ManufacturersRepository
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    val manufacturers = queryFlow.combine(repository.manufacturers) { query, manufacturers ->
        if (query.isEmpty()) manufacturers else manufacturers.filter { it.name.contains(query) }
    }
}