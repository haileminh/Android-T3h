package net.hailm.mediademo.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import net.hailm.mediademo.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 25/11/2017.
 */

public class SongManager {
    public SongManager() {
    }

    public List<Song> getSongs(Context context) {
        String[] projections = new String[]{
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION};

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projections, null, null,
                MediaStore.Audio.Media.TITLE + " ASC");

        List<Song> result = new ArrayList<>();
        if (cursor == null) {
            return result;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return result;
        }

        cursor.moveToFirst();
        int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int durationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

        while (!cursor.isAfterLast()) {
            String data = cursor.getString(dataIndex);
            String title = cursor.getString(titleIndex);
            String artist = cursor.getString(artistIndex);
            long duration = cursor.getLong(durationIndex);

            result.add(new Song(data, title, artist, duration));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
