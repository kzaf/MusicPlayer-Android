package com.zaf.exomusicplayer.ui.player;

import androidx.lifecycle.MutableLiveData;


public interface IPlayerViewModel {

    MutableLiveData<Integer> updatePlayImage();

    MutableLiveData<Integer> updateNextImage();

    MutableLiveData<Integer> updatePreviousImage();

    MutableLiveData<Boolean> isPlay();

    void togglePlayPauseButton(boolean isPlay);
}
