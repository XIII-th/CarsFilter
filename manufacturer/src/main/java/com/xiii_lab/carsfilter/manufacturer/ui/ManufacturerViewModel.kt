package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.lifecycle.ViewModel
import com.xiii_lab.carsfilter.manufacturer.data.ManufacturerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
@HiltViewModel
internal class ManufacturerViewModel @Inject constructor(
    private val repository: ManufacturerRepository
) : ViewModel()