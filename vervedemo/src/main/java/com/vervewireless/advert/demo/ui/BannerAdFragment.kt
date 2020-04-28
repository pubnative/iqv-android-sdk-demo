package com.vervewireless.advert.demo.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.vervewireless.advert.*
import com.vervewireless.advert.demo.R
import net.pubnative.lite.sdk.utils.Logger

class BannerAdFragment : Fragment(), AdListener, AdClickedListener {
    val TAG = BannerAdFragment::class.java.simpleName
    private lateinit var banner: AdView
    private lateinit var loadButton: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_banner, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        banner = view.findViewById(R.id.banner)
        banner.setAdListener(this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        banner.setAdSize(AdSize.BANNER)
        val adRequest = AdRequest()
        banner.requestAd("2", adRequest)
    }

    override fun onDestroy() {
        if (banner != null) {
            banner.destroy()
        }
        super.onDestroy()
    }

    // --------------- AdListener --------------------
    override fun onAdLoaded(response: AdResponse?) {
        Logger.d(TAG, "onAdLoaded")
    }

    override fun onAdError(e: AdError?) {
        Logger.e(TAG, "AdError", e?.cause)
    }

    override fun onAdPageFinished() {
        Logger.d(TAG, "onAdPageFinished")
    }

    override fun onNoAdReturned(response: AdResponse?) {
        Logger.d(TAG, "onNoAdReturned")
    }

    // --------------- AdClickedListener --------------------
    override fun onAdClicked(ad: Ad?, uri: Uri?): Boolean {
        Logger.d(TAG, "onAdClicked")
        return false
    }
}
