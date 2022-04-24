package com.xiii_lab.carsfilter.manufacturer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xiii_lab.carsfilter.manufacturer.R

/**
 * Created by XIII-th on 24.04.2022
 */
class ManufacturerFragment : Fragment() {

    private val viewModel: ManufacturerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manufacturer_fragment, container, false)
    }
}