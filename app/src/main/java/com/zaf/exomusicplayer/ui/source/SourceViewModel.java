package com.zaf.exomusicplayer.ui.source;

import android.content.Context;
import android.os.Environment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.adapter.SourceListAdapter;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.io.File;
import java.util.ArrayList;

public class SourceViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<RecyclerView> mRecyclerView;

    public SourceViewModel() {
        mText = new MutableLiveData<>();
        mRecyclerView = new MutableLiveData<>();

        mText.setValue("This is source fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<RecyclerView> getRecyclerView() {
        return mRecyclerView;
    }


    public ArrayList<SourceListItem> getDirectory(String pathname) {

        ArrayList<SourceListItem> directories = new ArrayList<>();

        SourceListItem sourceListItem;
        File file = new File(pathname);
        File[] root = file.listFiles();

        if (root != null) {
            for (File inFile : root) {
                if (inFile.isDirectory()) {
                    sourceListItem = new SourceListItem(R.drawable.ic_source_black_24dp, inFile.getName());
                }else {
                    sourceListItem = new SourceListItem(R.drawable.ic_insert_drive_file_black_24dp, inFile.getName());
                }
                directories.add(sourceListItem);
            }
        }else {
            return null;
        }
        return directories;
    }
}