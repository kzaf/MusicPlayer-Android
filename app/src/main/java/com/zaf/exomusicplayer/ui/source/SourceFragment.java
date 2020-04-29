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
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.adapter.SourceListAdapter;
import com.zaf.exomusicplayer.databinding.FragmentSourceBinding;
import com.zaf.exomusicplayer.model.SourceListItem;
import com.zaf.exomusicplayer.utils.Permissions;

import java.util.ArrayList;

public class SourceFragment extends Fragment implements SourceListAdapter.SourceListAdapterListItemClickListener {

    private SourceViewModel viewModel;
    private FragmentSourceBinding binding;
    private RecyclerView sourceListRecyclerView;
    private ArrayList<SourceListItem> directories;
    private String rootDirPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Permissions permissions = new Permissions(getActivity());
        permissions.checkStoragePermission();

        rootDirPath = Environment.getRootDirectory().toString();
        directories = new ArrayList<>();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSourceBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SourceViewModel.class);

        initializeView();

        generateRecyclerViewList(rootDirPath);

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

    @Override
    public void onListItemClick(int item) {
        generateRecyclerViewList(rootDirPath + "/" + directories.get(item).getName());
    }

    private void generateRecyclerViewList(String pathname){

        if (viewModel.getDirectory(pathname) == null)
            return;

        directories = viewModel.getDirectory(pathname);

        SourceListAdapter adapter = new SourceListAdapter(this, directories);

        binding.sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.sourceRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
