package com.example.zad3.ui.BMI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BMIViewModel : ViewModel() {
    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is BMI fragment"
    }
}