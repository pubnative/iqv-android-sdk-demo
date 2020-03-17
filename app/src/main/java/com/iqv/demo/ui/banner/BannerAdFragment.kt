package com.iqv.demo.ui.banner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.iqv.demo.R
import com.iqv.models.AdSize
import com.iqv.views.AdView

class BannerAdFragment : Fragment(), AdView.Listener {
    val TAG = BannerAdFragment::class.java.simpleName
    private lateinit var bannerAdViewModel: BannerAdViewModel
    private lateinit var banner: AdView
    private lateinit var loadButton: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bannerAdViewModel =
                ViewModelProviders.of(this).get(BannerAdViewModel::class.java)
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

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

    // --------------- PNAdView Listener --------------------
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
