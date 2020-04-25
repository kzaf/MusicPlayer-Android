package com.zaf.exomusicplayer.ui.profile;

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
import com.zaf.exomusicplayer.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding fragmentProfileBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = fragmentProfileBinding.getRoot();

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                fragmentProfileBinding.textNotifications.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentProfileBinding = null;
    }
}
