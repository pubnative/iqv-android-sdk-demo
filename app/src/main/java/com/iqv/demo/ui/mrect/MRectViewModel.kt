package com.iqv.demo.ui.mrect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MRectViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is MRECT Fragment"
    }
    val text: LiveData<String> = _text
}