package com.iqv.demo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iqv.demo.R
import net.pubnative.lite.sdk.models.AdSize
import net.pubnative.lite.sdk.views.HyBidAdView

class MRectAdFragment : Fragment(), HyBidAdView.Listener {

    val TAG = MRectAdFragment::class.java.simpleName
    private lateinit var banner: HyBidAdView
    private lateinit var loadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_mrect, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        banner = view.findViewById(R.id.mrect)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    private fun loadAd() {
        // supported sizes are currently 300x250, 320x50, 320x100, 728x90
        banner.setAdSize(AdSize.SIZE_300x250)
        banner.load("5",this)
    }

    // --------------- AdView Listener --------------------
    override fun onAdImpression() {
        Log.d(TAG, "onAdImpression")
    }

    override fun onAdLoadFailed(p0: Throwable?) {
        Log.d(TAG, "onAdLoadFailed")
    }

    override fun onAdClick() {
        Log.d(TAG, "onAdClick")
    }

    override fun onAdLoaded() {
        Log.d(TAG, "onAdLoaded")
        // render the banner
        banner.show();
    }

    override fun onDestroy() {
        if (banner != null) {
            banner.destroy()
        }
        super.onDestroy()
    }
}
