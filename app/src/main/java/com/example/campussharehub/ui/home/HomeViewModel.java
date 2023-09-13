package com.example.campussharehub.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("HomeViewModel.java is where we're putting the product listing");
    }

    public LiveData<String> getText() {
        return mText;
    }
}