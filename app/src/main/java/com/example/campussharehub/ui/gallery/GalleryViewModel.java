package com.example.campussharehub.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("GalleryViewModel.java is where we're putting the selling screen");
    }

    public LiveData<String> getText() {
        return mText;
    }
}