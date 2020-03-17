package com.iqv.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iqv.AdSdk
import com.iqv.AdSdk.InitialisationListener
import com.iqv.utils.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdSdk()

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun initAdSdk() {
        AdSdk.initialize(
            "dde3c298b47648459f8ada4a982fa92d",
            "adsdkexample",
            this.application
        ) {
            // AdSdk has been initialised
        }

        AdSdk.setLogLevel(Logger.Level.debug)

        AdSdk.setTestMode(true)
        AdSdk.setCoppaEnabled(false)
        AdSdk.setAge("30")
        AdSdk.setGender("male")
    }
}
