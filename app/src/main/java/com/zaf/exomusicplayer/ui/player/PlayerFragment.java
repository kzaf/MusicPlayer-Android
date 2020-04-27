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

    private PlayerViewModel viewModel;
    private FragmentPlayerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentPlayerBinding binding = FragmentPlayerBinding.inflate(inflater, container, false);
        this.binding = binding;
        this.viewModel = new ViewModelProvider(this).get(PlayerViewModel.class);

        initializePlayerButtons();
        handlePlayPauseButtonClick();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Set the images to the player buttons
     */
    private void initializePlayerButtons(){

        viewModel.updatePlayImage().observe(getViewLifecycleOwner(), drawablePath ->
                binding.includePlayer.playButton.setImageResource(drawablePath));

        viewModel.updateNextImage().observe(getViewLifecycleOwner(), drawablePath ->
                binding.includePlayer.nextButton.setImageResource(drawablePath));

        viewModel.updatePreviousImage().observe(getViewLifecycleOwner(), drawablePath ->
                binding.includePlayer.previousButton.setImageResource(drawablePath));

        viewModel.isPlay().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                binding.includePlayer.playButton.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            else
                binding.includePlayer.playButton.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        });

    }

    /**
     * Handle play button click event
     */
    private void handlePlayPauseButtonClick() {

        binding.includePlayer.playButton.setOnClickListener(v ->
                viewModel.togglePlayPauseButton(false));
    }

}
