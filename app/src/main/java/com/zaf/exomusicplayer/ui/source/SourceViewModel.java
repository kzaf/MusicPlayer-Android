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

    private MutableLiveData<ArrayList<SourceListItem>> directoriesLiveData;

    public SourceViewModel(Application application) {
        super(application);
        directoriesLiveData = new MutableLiveData<>();

        directoriesLiveData.setValue(getAllMusic());
    }

    public MutableLiveData<ArrayList<SourceListItem>> getDirectories() {
        return directoriesLiveData;
    }

//    public ArrayList<SourceListItem> getDirectory(String pathname) {
//
//        ArrayList<SourceListItem> directories = new ArrayList<>();
//
//        SourceListItem sourceListItem;
//        File file = new File(pathname);
//        File[] root = file.listFiles();
//
//        if (root != null) {
//            for (File inFile : root) {
//                if (inFile.isDirectory()) {
//                    sourceListItem = new SourceListItem(R.drawable.ic_source_black_24dp, inFile.getName());
//                } else {
//                    sourceListItem = new SourceListItem(R.drawable.ic_insert_drive_file_black_24dp, inFile.getName());
//                }
//                directories.add(sourceListItem);
//            }
//        } else {
//            return null;
//        }
//        return directories;
//    }

    private ArrayList<SourceListItem> getAllMusic(){

        ArrayList<SourceListItem> artst = new ArrayList<>();

        ContentResolver contentResolver = getApplication().getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] columns = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.ALBUM
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        Cursor cursor = contentResolver.query(uri, columns, selection, null, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{

                try {

                    int songId = Integer.parseInt(cursor.getString(0));
                    String songArtist = cursor.getString(1);
                    String songTitle = cursor.getString(2);
                    String songData = cursor.getString(3);
                    String songDisplayName = cursor.getString(4);
                    int songDuration = Integer.parseInt(cursor.getString(5));
                    String songYear = cursor.getString(6);
                    String songAlbum = cursor.getString(7);

                    artst.add(new SourceListItem(songId,
                            R.drawable.ic_player_black_24dp,
                            songTitle,
                            songArtist,
                            songYear,
                            songDuration,
                            songAlbum));

                }catch (Exception e){
                    String a = e.getMessage();
                }

            }while(cursor.moveToNext());
        }
        cursor.close();

        return artst;
    }

}