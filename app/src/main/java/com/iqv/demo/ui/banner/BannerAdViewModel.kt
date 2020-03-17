package com.iqv.demo.ui.banner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BannerAdViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is banner Fragment"
    }
    val text: LiveData<String> = _text
}