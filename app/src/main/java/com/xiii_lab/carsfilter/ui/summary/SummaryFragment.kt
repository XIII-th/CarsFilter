package com.xiii_lab.carsfilter.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.R
import com.xiii_lab.carsfilter.databinding.EmptySummaryFragmentBinding
import com.xiii_lab.carsfilter.databinding.SummaryFragmentBinding
import com.xiii_lab.carsfilter.navigation.openManufacturersSelection

/**
 * Created by XIII-th on 24.04.2022
 */
internal class SummaryFragment : Fragment() {

    private val vm: SummaryViewModel by viewModels<SummaryViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = if (vm.isDataSelected)
            SummaryFragmentBinding.inflate(inflater, container, false)
        else
            EmptySummaryFragmentBinding.inflate(inflater, container, false).apply {
                openFilter.setOnClickListener {
                    findNavController().openManufacturersSelection()
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