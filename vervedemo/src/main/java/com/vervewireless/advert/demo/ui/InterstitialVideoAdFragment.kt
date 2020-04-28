package com.vervewireless.advert.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.vervewireless.advert.*
import com.vervewireless.advert.demo.R
import com.vervewireless.advert.vast.VideoAdRequest
import net.pubnative.lite.sdk.utils.Logger
import net.pubnative.lite.sdk.vrv.VastResponse

class InterstitialVideoAdFragment : Fragment(), InterstitialVideoAd.InterstitialVideoAdListener {
    val TAG = InterstitialVideoAdFragment::class.java.simpleName
    private lateinit var loadButton: Button
    private lateinit var interstitialVideo: InterstitialVideoAd

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_interstitial_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        interstitialVideo = InterstitialVideoAd(context, "15")
        interstitialVideo.setInterstitialVideoAdListener(this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        val adRequest = VideoAdRequest()
        interstitialVideo.requestAd(adRequest)
    }

    override fun onDestroy() {
        if (interstitialVideo != null) {
            interstitialVideo.destroy()
        }
        super.onDestroy()
    }

    override fun onAdReady() {
        Logger.d(TAG, "onAdReady")
        interstitialVideo.display()
    }

    override fun onAdFailed(errorCode: Int) {
        Logger.e(TAG, "onAdFailed: " + VastResponse.parseErrorMessage(errorCode))
    }

    override fun onNoAdReturned() {
        Logger.d(TAG, "onNoAdReturned")
    }

    override fun onAdClosed() {
        Logger.d(TAG, "onAdClosed")
    }

    override fun onVideoError() {
        Logger.d(TAG, "onVideoError")
    }

    override fun onLeaveApplication() {
        Logger.d(TAG, "onLeaveApplication")
    }
}