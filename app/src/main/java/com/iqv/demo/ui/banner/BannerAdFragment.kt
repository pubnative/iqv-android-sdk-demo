package com.iqv.demo.ui.banner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iqv.demo.R
import com.iqv.views.BannerAdView
import com.iqv.views.PNAdView

class BannerAdFragment : Fragment(), PNAdView.Listener {
    val TAG = BannerAdFragment::class.java.simpleName
    private lateinit var bannerAdViewModel: BannerAdViewModel
    private lateinit var banner: BannerAdView
    private lateinit var loadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bannerAdViewModel =
            ViewModelProviders.of(this).get(BannerAdViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_banner, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        bannerAdViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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
    }

    override fun onDestroy() {
        if (banner != null) {
            banner.destroy()
        }
        super.onDestroy()
    }
}
