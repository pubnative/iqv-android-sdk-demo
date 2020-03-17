package com.iqv.demo.ui.mrect

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

class MRectFragment : Fragment(), AdView.Listener {

    val TAG = MRectFragment::class.java.simpleName
    private lateinit var mrectViewModel: MRectViewModel
    private lateinit var banner: AdView
    private lateinit var loadButton: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mrectViewModel =
                ViewModelProviders.of(this).get(MRectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_mrect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton = view.findViewById(R.id.button_load)
        banner = view.findViewById(R.id.mrect)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    fun loadAd() {
        // supported sizes are currently 300x250, 320x50, 320x100, 728x90
        banner.setAdSize(AdSize.SIZE_300x250)
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
