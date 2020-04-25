package com.zaf.exomusicplayer.ui.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.databinding.FragmentPlayerBinding;

public class PlayerFragment extends Fragment {

    private PlayerViewModel playerViewModel;
    private FragmentPlayerBinding fragmentPlayerBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater, container, false);

        View root = fragmentPlayerBinding.getRoot();

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                fragmentPlayerBinding.textPlayer.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPlayerBinding = null;
    }
}
