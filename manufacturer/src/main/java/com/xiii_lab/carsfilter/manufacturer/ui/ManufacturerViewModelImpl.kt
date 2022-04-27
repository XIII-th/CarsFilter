package com.xiii_lab.carsfilter.manufacturer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiii_lab.carsfilter.manufacturer.data.ManufacturersRepository
import com.xiii_lab.carsfilter.manufacturer.data.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by XIII-th on 24.04.2022
 */
@HiltViewModel
internal class ManufacturerViewModelImpl @Inject constructor(
    repository: ManufacturersRepository
) : ViewModel(), ManufacturerViewModel {

    override val manufacturers = repository.manufacturers

    override val selectedManufacturer = MutableSharedFlow<Manufacturer>()

    override fun onSelected(manufacturer: Manufacturer) {
        viewModelScope.launch {
            selectedManufacturer.emit(manufacturer)
        }
    }
}