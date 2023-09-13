package com.example.campussharehub.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("SlideshowViewModel.java will be the messaging bit (definitely won't get 'round to this...)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}