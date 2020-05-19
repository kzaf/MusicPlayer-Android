package com.zaf.exomusicplayer.ui.source;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zaf.exomusicplayer.adapter.SourceListAdapter;
import com.zaf.exomusicplayer.databinding.FragmentSourceBinding;
import com.zaf.exomusicplayer.utils.Permissions;

public class SourceFragment extends Fragment implements SourceListAdapter.SourceListAdapterListItemClickListener {

    private SourceViewModel viewModel;
    private FragmentSourceBinding binding;
    private SourceListAdapter sourceListAdapter;
    private String sourceCurrentPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Permissions permissions = new Permissions(getActivity());
        permissions.checkStoragePermission();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSourceBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SourceViewModel.class);

        initializeView();

        sourceCurrentPath = Environment.getRootDirectory().toString();

        return binding.getRoot();
    }

    private void initializeView() {

        viewModel.getText().observe(getViewLifecycleOwner(), s ->
                binding.textSource.setText(s));

        viewModel.getDirectories().observe(getViewLifecycleOwner(), sourceListItems -> {
            sourceListAdapter = new SourceListAdapter(this, sourceListItems);
            binding.sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.sourceRecyclerView.setAdapter(sourceListAdapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListItemClick(int item) {
//        String rootDirPath = sourceCurrentPath + "/" + viewModel.getDirectories().getValue().get(item).getName();
//        viewModel.getDirectories().setValue(viewModel.getDirectory(rootDirPath));
//        sourceCurrentPath = sourceCurrentPath + rootDirPath;

    }
}