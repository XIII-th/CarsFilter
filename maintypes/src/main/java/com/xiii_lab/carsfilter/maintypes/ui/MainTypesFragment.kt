package com.xiii_lab.carsfilter.maintypes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xiii_lab.carsfilter.design.databinding.ListFragmentBinding
import com.xiii_lab.carsfilter.design.list.LoadingStateAdapter
import com.xiii_lab.carsfilter.design.search.attachToMenu
import com.xiii_lab.carsfilter.maintypes.ui.list.MainTypeAdapter
import com.xiii_lab.carsfilter.navigation.openBuildDateSelection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Created by XIII-th on 27.04.2022
 */
@AndroidEntryPoint
internal class MainTypesFragment : Fragment() {

    private val viewModel: MainTypesViewModel by viewModels<MainTypesViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        val adapter = MainTypeAdapter(viewModel::onSelected)
        list.adapter = adapter.withLoadStateFooter(LoadingStateAdapter { adapter.retry() })
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mainTypes.collect { manufacturers ->
                adapter.submitData(manufacturers)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedMainType.collect { (manufacturer, mainType) ->
                findNavController().openBuildDateSelection(manufacturer, mainType)
            }
        }
    }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        viewModel.attachToMenu(menu, inflater)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        with(requireActivity() as AppCompatActivity) {
            supportActionBar?.apply {
                title = viewModel.toolbarTitle
                subtitle = null
            }
        }
    }
}