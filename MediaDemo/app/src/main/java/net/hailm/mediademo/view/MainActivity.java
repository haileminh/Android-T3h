package net.hailm.mediademo.view;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.hailm.mediademo.R;
import net.hailm.mediademo.adapter.SongAdapter;
import net.hailm.mediademo.manager.SongPlayer;
import net.hailm.mediademo.manager.SongPlayerImpl;
import net.hailm.mediademo.model.Song;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnItemClickListener {
    private RecyclerView rcvSongs;
    private SongAdapter songAdapter;

    private SongPlayer songPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        songPlayer = new SongPlayerImpl(this);
    }
    private void initializeComponents() {
        rcvSongs = findViewById(R.id.rcv_songs);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcvSongs.setLayoutManager(llm);


        songAdapter = new SongAdapter(this);
        songAdapter.setOnItemClickListener(this);
        rcvSongs.setAdapter(songAdapter);
    }

    @Override
    public void onClicked(int position) {
        Toast.makeText(this, "Positon: " + position, Toast.LENGTH_SHORT).show();

        Song song = songAdapter.getItem(position);

        int state = songPlayer.getState();

        if (state != SongPlayerImpl.STATE_IDLE) {
            songPlayer.stop();
        }

        songPlayer.play(Uri.parse(song.getData()));
    }
}

