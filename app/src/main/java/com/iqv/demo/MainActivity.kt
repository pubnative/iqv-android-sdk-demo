package com.iqv.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import net.pubnative.lite.sdk.HyBid
import net.pubnative.lite.sdk.utils.Logger

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
            R.id.navigation_banner, R.id.navigation_mrect, R.id.navigation_interstitial))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun initAdSdk() {
        HyBid.initialize(
            "dde3c298b47648459f8ada4a982fa92d",
            this.application
        ) {
            // AdSdk has been initialised
        }

        HyBid.setLogLevel(Logger.Level.debug)

        HyBid.setTestMode(true)
        HyBid.setCoppaEnabled(false)
        HyBid.setAge("30")
        HyBid.setGender("male")

        HyBid.getUserDataManager().iabusPrivacyString = "1YYN"
    }
}
