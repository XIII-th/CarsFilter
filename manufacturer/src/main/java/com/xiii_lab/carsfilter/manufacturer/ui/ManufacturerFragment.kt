package com.xiii_lab.carsfilter.manufacturer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding
import com.xiii_lab.carsfilter.design.list.ListState
import com.xiii_lab.carsfilter.design.list.LoadingStateAdapter
import com.xiii_lab.carsfilter.design.search.attachToMenu
import com.xiii_lab.carsfilter.manufacturer.ui.list.ManufacturerAdapter
import com.xiii_lab.carsfilter.navigation.openMainTypeSelection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Created by XIII-th on 24.04.2022
 */
@AndroidEntryPoint
class ManufacturerFragment : Fragment() {

    private val viewModel: ManufacturerViewModel by viewModels<ManufacturerViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        val adapter = ManufacturerAdapter(viewModel::onSelected)
        list.adapter = adapter.withLoadStateFooter(LoadingStateAdapter { adapter.retry() })
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.manufacturers.collect { manufacturers ->
                adapter.submitData(manufacturers)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedManufacturer.collect { manufacturer ->
                findNavController().openMainTypeSelection(manufacturer)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { state ->
                viewModel.onLoadStateUpdated(state, adapter.itemCount)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reload.collect {
                adapter.retry()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listState.collect { state ->
                when (state) {
                    ListState.Progress -> {
                        progress.isVisible = true
                        list.isVisible = false
                        placeholder.root.isVisible = false
                    }
                    ListState.Data -> {
                        progress.isVisible = false
                        list.isVisible = true
                        placeholder.root.isVisible = false
                    }
                    is ListState.Placeholder -> {
                        progress.isVisible = false
                        list.isVisible = false
                        placeholder.apply {
                            root.isVisible = true
                            placeholderImage.setImageResource(state.iconRes)
                            placeholderComment.setText(state.commentRes)
                        }
                    }
                }
            }
        }
    }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item = viewModel.attachToMenu(menu, inflater)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchEnabled.collect { isEnabled ->
                if (!isEnabled) item.collapseActionView()
                item.isVisible = isEnabled
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}