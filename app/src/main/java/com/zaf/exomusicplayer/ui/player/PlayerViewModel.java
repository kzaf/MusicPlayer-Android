package com.zaf.exomusicplayer.ui.player;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zaf.exomusicplayer.R;

public class PlayerViewModel extends ViewModel {

    private MutableLiveData<Integer> mPlayImage;
    private MutableLiveData<Integer> mNextImage;
    private MutableLiveData<Integer> mPreviousImage;

    public PlayerViewModel() {
        mPlayImage = new MutableLiveData<>();
        mNextImage = new MutableLiveData<>();
        mPreviousImage = new MutableLiveData<>();

        mPlayImage.setValue(R.drawable.ic_play_circle_outline_black_24dp);
        mNextImage.setValue(R.drawable.ic_skip_next_black_24dp);
        mPreviousImage.setValue(R.drawable.ic_skip_previous_black_24dp);
    }

    LiveData<Integer> updatePlayImage(){
        return mPlayImage;
    }

    LiveData<Integer> updateNextImage(){
        return mNextImage;
    }

    LiveData<Integer> updatePreviousImage(){
        return mPreviousImage;
    }
}