package com.iqv.demo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iqv.demo.R
import com.iqv.rewarded.RewardedAd

class RewardedAdFragment : Fragment(), RewardedAd.Listener {
    val TAG = RewardedAdFragment::class.java.simpleName
    private lateinit var loadButton: Button
    private lateinit var rewardedAd: RewardedAd

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
        rewardedAd = RewardedAd(context, "4", this)

        loadButton.setOnClickListener {
            loadAd()
        }
    }

    private fun loadAd() {
        rewardedAd.load()
    }

    override fun onRewardedLoaded() {
        Log.d(TAG, "onRewardedLoaded")
        // Display the rewarded ad
        rewardedAd.show();
    }

    override fun onRewardedLoadFailed(error: Throwable?) {
        Log.e(TAG, "onRewardedLoadFailed", error)
    }

    override fun onRewardedOpened() {
        Log.d(TAG, "onRewardedOpened")
    }

    override fun onRewardedClick() {
        Log.d(TAG, "onRewardedClick")
    }

    override fun onRewardedClosed() {
        Log.d(TAG, "onRewardedClosed")
    }

    override fun onReward() {
        Log.d(TAG, "onReward")
    }

    override fun onDestroy() {
        if (rewardedAd != null) {
            rewardedAd.destroy()
        }
        super.onDestroy()
    }
}