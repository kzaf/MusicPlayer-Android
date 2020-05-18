package com.zaf.exomusicplayer.ui.source;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.io.File;
import java.util.ArrayList;

public class SourceViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<SourceListItem>> directoriesLiveData;
//    private ArrayList<SourceListItem> directoriesList;

    public SourceViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        directoriesLiveData = new MutableLiveData<>();

        mText.setValue(" <- Back");

//        directoriesList = new ArrayList<>();
//        directoriesList = getDirectory(Environment.getRootDirectory().toString());
        directoriesLiveData.setValue(getAllMusic());
    }

    public MutableLiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<ArrayList<SourceListItem>> getDirectories() {
        return directoriesLiveData;
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

    private ArrayList<SourceListItem> getAllMusic(){

        ArrayList<SourceListItem> artst = new ArrayList<>();

        ContentResolver contentResolver = getApplication().getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] columns = {MediaStore.Audio.Media.DISPLAY_NAME};

        Cursor cursor = contentResolver.query(uri, columns, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                artst.add(new SourceListItem(R.drawable.ic_insert_drive_file_black_24dp, artist));
            }while(cursor.moveToNext());
        }
        cursor.close();

        return artst;
    }

}