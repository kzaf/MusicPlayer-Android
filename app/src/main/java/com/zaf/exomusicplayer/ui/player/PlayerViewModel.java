package com.zaf.exomusicplayer.ui.player;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.zaf.exomusicplayer.R;

public class PlayerViewModel extends AndroidViewModel implements IPlayerViewModel {

    private MutableLiveData<Integer> mPlayImage;
    private MutableLiveData<Integer> mNextImage;
    private MutableLiveData<Integer> mPreviousImage;
    private MutableLiveData<Boolean> mIsPlay;

    public PlayerViewModel(@NonNull Application application) {
        super(application);

        this.mPlayImage = new MutableLiveData<>();
        this.mNextImage = new MutableLiveData<>();
        this.mPreviousImage = new MutableLiveData<>();
        this.mIsPlay = new MutableLiveData<>();

        this.mPlayImage.setValue(R.drawable.ic_play_circle_outline_black_24dp);
        this.mNextImage.setValue(R.drawable.ic_skip_next_black_24dp);
        this.mPreviousImage.setValue(R.drawable.ic_skip_previous_black_24dp);
        this.mIsPlay.setValue(false);
    }


    @Override
    public MutableLiveData<Integer> updatePlayImage(){
        return this.mPlayImage;
    }

    @Override
    public MutableLiveData<Integer> updateNextImage(){
        return this.mNextImage;
    }

    @Override
    public MutableLiveData<Integer> updatePreviousImage(){
        return this.mPreviousImage;
    }

    @Override
    public MutableLiveData<Boolean> isPlay(){
        return this.mIsPlay;
    }

    @Override
    public void togglePlayPauseButton(boolean isPlay){

        if (this.mIsPlay.getValue()){
            this.mIsPlay.setValue(false);
        }else {
            this.mIsPlay.setValue(true);
        }
    }

    Integer updatePlayPauseResource(boolean aBoolean){
        if (aBoolean)
            mPlayImage.setValue(R.drawable.ic_pause_circle_outline_black_24dp);
        else
            mPlayImage.setValue(R.drawable.ic_play_circle_outline_black_24dp);

        return mPlayImage.getValue();
    }

}