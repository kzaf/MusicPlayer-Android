package com.zaf.exomusicplayer.ui.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.databinding.FragmentPlayerBinding;

public class PlayerFragment extends Fragment {

    private PlayerViewModel playerViewModel;
    private FragmentPlayerBinding fragmentPlayerBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater, container, false);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);

        initializePlayerButtons();
        setupPlayPauseButtonClick();

        return fragmentPlayerBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPlayerBinding = null;
    }

    /**
     * Set the images to the player buttons
     */
    private void initializePlayerButtons(){
        playerViewModel
                .updatePlayImage()
                .observe(getViewLifecycleOwner(),
                        drawable -> fragmentPlayerBinding.includePlayer.playButton.setImageResource(drawable));

        playerViewModel
                .updateNextImage()
                .observe(getViewLifecycleOwner(),
                        drawable -> fragmentPlayerBinding.includePlayer.nextButton.setImageResource(drawable));

        playerViewModel
                .updatePreviousImage()
                .observe(getViewLifecycleOwner(),
                        drawable -> fragmentPlayerBinding.includePlayer.previousButton.setImageResource(drawable));

    }

    /**
     * Handle play button click event
     */
    private void setupPlayPauseButtonClick() {

        fragmentPlayerBinding.includePlayer.playButton
                .setOnClickListener(view ->
                        fragmentPlayerBinding.includePlayer.playButton
                                .setImageResource(R.drawable.ic_pause_circle_outline_black_24dp));
    }

}
