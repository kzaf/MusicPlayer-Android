package com.zaf.exomusicplayer.ui.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SourceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SourceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is source fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}