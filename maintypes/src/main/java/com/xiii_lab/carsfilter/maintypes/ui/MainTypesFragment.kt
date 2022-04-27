package com.xiii_lab.carsfilter.maintypes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding
import com.xiii_lab.carsfilter.maintypes.ui.list.MainTypeAdapter
import com.xiii_lab.carsfilter.navigation.openBuildDateSelection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by XIII-th on 27.04.2022
 */
@AndroidEntryPoint
internal class MainTypesFragment : Fragment() {

    private val viewModel: MainTypesViewModel by viewModels<MainTypesViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        val adapter = MainTypeAdapter(viewModel::onSelected)
        list.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mainTypes.collect { manufacturers ->
                adapter.submitData(manufacturers)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedMainType.collect { (manufacturerId, mainTypeId) ->
                findNavController().openBuildDateSelection(manufacturerId, mainTypeId)
            }
        }
    }.root
}