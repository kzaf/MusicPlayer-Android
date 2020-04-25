package com.zaf.exomusicplayer.ui.source;

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
import com.zaf.exomusicplayer.databinding.FragmentSourceBinding;

public class SourceFragment extends Fragment {

    private SourceViewModel sourceViewModel;
    private FragmentSourceBinding fragmentSourceBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fragmentSourceBinding = FragmentSourceBinding.inflate(inflater, container, false);
        View root = fragmentSourceBinding.getRoot();

        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel.class);
        sourceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                fragmentSourceBinding.textSource.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentSourceBinding = null;
    }
}
