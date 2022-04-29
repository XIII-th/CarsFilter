package com.xiii_lab.carsfilter.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.R
import com.xiii_lab.carsfilter.databinding.EmptySummaryFragmentBinding
import com.xiii_lab.carsfilter.databinding.SummaryFragmentBinding
import com.xiii_lab.carsfilter.design.connectivity.showNoConnectionNotification
import com.xiii_lab.carsfilter.navigation.openManufacturersSelection
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by XIII-th on 24.04.2022
 */
@AndroidEntryPoint
internal class SummaryFragment : Fragment() {

    private val viewModel: SummaryViewModel by viewModels<SummaryViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = if (viewModel.isDataSelected)
            SummaryFragmentBinding.inflate(inflater, container, false).apply {
                manufacturer.text = viewModel.manufacturer
                mainType.text = viewModel.mainType
                buildDate.text = viewModel.buildDate
                openFilter.setOnClickListener {
                    viewModel.onNewFilterRequested()
                }
            }
        else
            EmptySummaryFragmentBinding.inflate(inflater, container, false).apply {
                openFilter.setOnClickListener {
                    viewModel.onNewFilterRequested()
                }
            }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.requestNewFilter.collect {
                findNavController().openManufacturersSelection()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.connectivityNotification.collect {
                binding.root.showNoConnectionNotification()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        with(requireActivity() as AppCompatActivity) {
            supportActionBar?.apply {
                setTitle(R.string.app_name)
                subtitle = null
            }
        }
    }
}