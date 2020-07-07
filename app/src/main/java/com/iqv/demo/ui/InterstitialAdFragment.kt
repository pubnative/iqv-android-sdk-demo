package com.iqv.demo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iqv.demo.R
import net.pubnative.lite.sdk.interstitial.HyBidInterstitialAd

class InterstitialAdFragment : Fragment(), HyBidInterstitialAd.Listener {
    val TAG = InterstitialAdFragment::class.java.simpleName
    private lateinit var loadButton: Button
    private lateinit var interstitial: HyBidInterstitialAd

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_interstitial, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        // Default Zone ID parameters are:
        // 3 - Interstitial Ads
        // 4 - Video Ads
        interstitial = HyBidInterstitialAd(context, "4", this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        interstitial.load()
    }

    override fun onInterstitialLoaded() {
        Log.d(TAG, "onInterstitialLoaded")
        // Display the interstitial
        interstitial.show();
    }

    override fun onInterstitialLoadFailed(error: Throwable?) {
        Log.e(TAG, "onInterstitialLoadFailed", error)
    }

    override fun onInterstitialImpression() {
        Log.d(TAG, "onInterstitialImpression")
    }

    override fun onInterstitialClick() {
        Log.d(TAG, "onInterstitialClick")
    }

    override fun onInterstitialDismissed() {
        Log.d(TAG, "onInterstitialDismissed")
    }

    override fun onDestroy() {
        if (interstitial != null) {
            interstitial.destroy()
        }
        super.onDestroy()
    }
}