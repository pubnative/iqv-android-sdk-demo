package com.iqv.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iqv.demo.R
import com.iqv.models.AdSize
import com.iqv.utils.Logger
import com.iqv.views.AdView

class BannerAdFragment : Fragment(), AdView.Listener {
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

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        // supported sizes are currently 300x250, 320x50, 320x100, 728x90
        banner.setAdSize(AdSize.SIZE_320x50)
        banner.load(this)
    }

    // --------------- AdView Listener --------------------
    override fun onAdImpression() {
        Logger.d(TAG, "onAdImpression")
    }

    override fun onAdLoadFailed(p0: Throwable?) {
        Logger.d(TAG, "onAdLoadFailed")
    }

    override fun onAdClick() {
        Logger.d(TAG, "onAdClick")
    }

    override fun onAdLoaded() {
        Logger.d(TAG, "onAdLoaded")
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
