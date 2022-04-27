package com.xiii_lab.carsfilter.maintypes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xiii_lab.carsfilter.maintypes.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by XIII-th on 27.04.2022
 */
@AndroidEntryPoint
internal class MainTypesFragment : Fragment() {

    private val viewModel: MainTypesViewModel by viewModels<MainTypesViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_types_fragment, container, false)
    }
}