package com.example.zad2.ui.BMR;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BMRViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BMRViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is BMR calc fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}