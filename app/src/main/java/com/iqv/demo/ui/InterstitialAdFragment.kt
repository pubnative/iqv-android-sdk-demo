package com.iqv.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iqv.demo.R
import com.iqv.interstitial.InterstitialAd
import com.iqv.utils.Logger

class InterstitialAdFragment : Fragment(), InterstitialAd.Listener {
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
        interstitial = InterstitialAd(context, this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        interstitial.load()
    }

    override fun onInterstitialLoaded() {
        Logger.d(TAG, "onInterstitialLoaded")
        // Display the interstitial
        interstitial.show();
    }

    override fun onInterstitialLoadFailed(error: Throwable?) {
        Logger.e(TAG, "onInterstitialLoadFailed", error)
    }

    override fun onInterstitialImpression() {
        Logger.d(TAG, "onInterstitialImpression")
    }

    override fun onInterstitialClick() {
        Logger.d(TAG, "onInterstitialClick")
    }

    override fun onInterstitialDismissed() {
        Logger.d(TAG, "onInterstitialDismissed")
    }

    override fun onDestroy() {
        if (interstitial != null) {
            interstitial.destroy()
        }
        super.onDestroy()
    }
}