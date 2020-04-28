package com.zaf.exomusicplayer.ui.source;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.databinding.FragmentSourceBinding;
import com.zaf.exomusicplayer.databinding.SourceListItemBinding;

public class SourceFragment extends Fragment {

    private SourceViewModel viewModel;
    private FragmentSourceBinding binding;
    private SourceListItemBinding sourceListItemBinding;
    private RecyclerView sourceListRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSourceBinding.inflate(inflater, container, false);
        sourceListItemBinding = SourceListItemBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SourceViewModel.class);

        viewModel.generateRecyclerViewList(getActivity(), binding.sourceRecyclerView, sourceListItemBinding);

        initializeView();

        return binding.getRoot();
    }

    private void initializeView() {

        viewModel.getText().observe(getViewLifecycleOwner(), s ->
                binding.textSource.setText(s));

        viewModel.getRecyclerView().observe(getViewLifecycleOwner(), recyclerView -> {
            sourceListRecyclerView = recyclerView;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
