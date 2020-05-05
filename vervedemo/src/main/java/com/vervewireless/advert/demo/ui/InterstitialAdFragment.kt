package com.vervewireless.advert.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.vervewireless.advert.AdError
import com.vervewireless.advert.AdRequest
import com.vervewireless.advert.InterstitialAd
import com.vervewireless.advert.InterstitialAdListener
import com.vervewireless.advert.demo.R
import net.pubnative.lite.sdk.utils.Logger

class InterstitialAdFragment : Fragment(), InterstitialAdListener {
    val TAG = InterstitialAdFragment::class.java.simpleName
    private lateinit var loadButton: Button
    private lateinit var interstitial: InterstitialAd

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_interstitial, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        interstitial = InterstitialAd(context, "3")
        interstitial.setInterstitialAdListener(this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        val adRequest = AdRequest()
        interstitial.requestAd(adRequest)
    }

    override fun onDestroy() {
        if (interstitial != null) {
            interstitial.destroy()
        }
        super.onDestroy()
    }

    override fun onAdReady() {
        Logger.d(TAG, "onAdReady")
        interstitial.display()
    }

    override fun onAdFailed(adError: AdError?) {
        Logger.e(TAG, "onAdFailed", adError?.cause)
    }

    override fun onNoAdReturned() {
        Logger.d(TAG, "onNoAdReturned")
    }

    override fun onAdClosed() {
        Logger.d(TAG, "onAdClosed")
    }
}