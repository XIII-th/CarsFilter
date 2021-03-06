package com.xiii_lab.carsfilter.builddtates.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.builddtates.ui.list.BuildDatesAdapter
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding
import com.xiii_lab.carsfilter.design.list.updateListState
import com.xiii_lab.carsfilter.navigation.openSummary
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Created by XIII-th on 27.04.2022
 */
@AndroidEntryPoint
class BuildDateFragment : Fragment() {

    private val viewModel: BuildDateViewModel by viewModels<BuildDateViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        val adapter = BuildDatesAdapter(viewModel::onSelected)
        list.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.buildDates.collect { (state, buildDates) ->
                adapter.submitList(buildDates)
                updateListState(state, buildDates.size, "")
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedBuildDate.collect { (manufacturer, mainType, buildDate) ->
                findNavController().openSummary(manufacturer.name, mainType.name, buildDate.date)
            }
        }
    }.root

    override fun onStart() {
        super.onStart()
        with(requireActivity() as AppCompatActivity) {
            supportActionBar?.apply {
                title = viewModel.toolbarTitle
                subtitle = viewModel.toolbarSubtitle
            }
        }
    }
}