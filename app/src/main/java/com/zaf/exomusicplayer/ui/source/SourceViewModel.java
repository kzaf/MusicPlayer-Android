package com.zaf.exomusicplayer.ui.source;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.adapter.SourceListAdapter;
import com.zaf.exomusicplayer.databinding.SourceListItemBinding;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.io.File;
import java.util.ArrayList;

public class SourceViewModel extends ViewModel implements SourceListAdapter.SourceListAdapterListItemClickListener {

    public static int READ_STORAGE_PERMISSION_REQUEST_CODE = 300;

    private MutableLiveData<String> mText;
    private MutableLiveData<RecyclerView> mRecyclerView;

    private RecyclerView recyclerView;

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

    public void generateRecyclerViewList(Context context, RecyclerView recyclerView, SourceListItemBinding sourceListItemBinding){

        checkStoragePermission(context);

        ArrayList<SourceListItem> directories = new ArrayList<>();

        if (!getRootDirectory(directories))
            return;

        SourceListAdapter adapter = new SourceListAdapter(this, directories, sourceListItemBinding);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private boolean getRootDirectory(ArrayList<SourceListItem> directories) {
        SourceListItem sourceListItem;
        File file = new File(Environment.getRootDirectory(), "");
        File[] root = file.listFiles();

        if (root != null) {
            for (File inFile : root) {
                if (inFile.isDirectory()) {
                    sourceListItem = new SourceListItem(null, inFile.getName());
                }else {
                    sourceListItem = new SourceListItem(null, inFile.getName());
                }
                directories.add(sourceListItem);
            }
        }else {
            return false;
        }
        return true;
    }

    private void checkStoragePermission(Context context) {
        if (!checkPermissionForReadExtertalStorage(context)){
            try {
                requestPermissionForReadExtertalStorage(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onListItemClick(int item) {

    }

    public boolean checkPermissionForReadExtertalStorage(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionForReadExtertalStorage(Context context) throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}