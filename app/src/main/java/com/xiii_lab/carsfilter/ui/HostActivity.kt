package com.xiii_lab.carsfilter.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.xiii_lab.carsfilter.R
import com.xiii_lab.carsfilter.databinding.HostActivityBinding
import com.xiii_lab.carsfilter.design.connectivity.showLostConnectionNotification
import com.xiii_lab.carsfilter.design.connectivity.showNoConnectionNotification
import com.xiii_lab.carsfilter.environment.connectivity.ConnectivityInfoDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.xiii_lab.carsfilter.design.R as DesignR

/**
 * Created by XIII-th on 24.04.2022
 */
@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    private lateinit var binding: HostActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var connectivityDataSource: ConnectivityInfoDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_summary))
        setupActionBarWithNavController(navController, appBarConfiguration)

        lifecycleScope.launchWhenStarted {
            connectivityDataSource.hasConnection.collect { hasConnection ->
                if (!hasConnection) binding.root.showLostConnectionNotification()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(DesignR.menu.no_connection_menu, menu)
        val item = menu.findItem(DesignR.id.action_no_connection)
        item.setOnMenuItemClickListener {
            binding.root.showNoConnectionNotification()
            true
        }
        lifecycleScope.launchWhenStarted {
            connectivityDataSource.hasConnection.collect { hasConnection ->
                item.isVisible = !hasConnection
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}