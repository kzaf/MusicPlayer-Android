package com.zaf.exomusicplayer.ui.source;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.util.ArrayList;

public class SourceViewModel extends AndroidViewModel {

    private static final String TAG = SourceViewModel.class.getName();
    private MutableLiveData<ArrayList<SourceListItem>> directoriesLiveData;

    public SourceViewModel(Application application) {
        super(application);
        directoriesLiveData = new MutableLiveData<>();

        directoriesLiveData.setValue(getAllMusic());
    }

    public MutableLiveData<ArrayList<SourceListItem>> getDirectories() {
        return directoriesLiveData;
    }

    private ArrayList<SourceListItem> getAllMusic(){

        ArrayList<SourceListItem> artst = new ArrayList<>();

        ContentResolver contentResolver = getApplication().getContentResolver();
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.YEAR,
                        MediaStore.Audio.Media.ALBUM
                },
                MediaStore.Audio.Media.IS_MUSIC + " != 0",
                null,
                null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                try {
                    String songId = cursor.getString(0);
                    String songArtist = cursor.getString(1);
                    String songTitle = cursor.getString(2);
                    String songData = cursor.getString(3);
                    String songDisplayName = cursor.getString(4);
                    String songDuration = cursor.getString(5);
                    String songYear = cursor.getString(6);
                    String songAlbum = cursor.getString(7);

                    artst.add(new SourceListItem(
                            Integer.parseInt(songId),
                            R.drawable.ic_player_black_24dp,
                            songTitle,
                            songArtist,
                            songYear,
                            Integer.parseInt(songDuration),
                            songAlbum,
                            songData));

                }catch (Exception e){
                    Log.e(TAG, "getAllMusic: " + e.getMessage());
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        return artst;
    }
}