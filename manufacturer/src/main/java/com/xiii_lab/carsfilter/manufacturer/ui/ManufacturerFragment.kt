package com.xiii_lab.carsfilter.manufacturer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding
import com.xiii_lab.carsfilter.manufacturer.ui.list.ManufacturerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by XIII-th on 24.04.2022
 */
@AndroidEntryPoint
class ManufacturerFragment : Fragment() {

    private val viewModel: ManufacturerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        val adapter = ManufacturerAdapter()
        list.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.manufacturers.collect { manufacturers ->
                adapter.submitData(manufacturers)
            }
        }
    }.root
}