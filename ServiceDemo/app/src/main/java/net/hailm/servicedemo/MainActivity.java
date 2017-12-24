package net.hailm.servicedemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.servicedemo.contands.Action;
import net.hailm.servicedemo.contands.Key;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNumberA, edtNumberB;
    private Button btnPlus;
    private TextView txtResult, btnPlayMusic;

    private MusicPlayerReceiver musicPlayerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        registerReceivers();
    }


    private void init() {
        edtNumberA = findViewById(R.id.edt_main_so_a);
        edtNumberB = findViewById(R.id.edt_main_so_b);
        txtResult = findViewById(R.id.txt_result);
        btnPlayMusic = findViewById(R.id.btn_play_music);
        btnPlus = findViewById(R.id.btn_tong);

        btnPlus.setOnClickListener(this);
        btnPlayMusic.setOnClickListener(this);
    }

    private void registerReceivers() {
        // Tạo receiver
        musicPlayerReceiver = new MusicPlayerReceiver();

        // Tạo bộ lọc Intent-filter
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_PLAY);

        //
        registerReceiver(musicPlayerReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(musicPlayerReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tong:
                addNumber();
                break;

            case R.id.btn_play_music:
                playMusic();
                break;

            default:
                break;
        }
    }

    private void playMusic() {
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra(Key.SONG_TITLE, "Mưa mùa thu.");
        intent.putExtra(Key.SONG_URL, "https://109a15170.vws.vegacdn.vn/KuRY_MQJ84ofZ3bEobvJDw/1513462272/media2/song/iphone/123/1015071/1015071.mp3");
        intent.putExtra(Key.SONG_ARTIST, "hailm");
        startService(intent);
    }

    private void addNumber() {
        String a = edtNumberA.getText().toString();
        String b = edtNumberB.getText().toString();

        if (a.isEmpty() || b.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        float numberA = Float.parseFloat(a);
        float numberB = Float.parseFloat(b);

        Intent intent = new Intent(this, CalculatorService.class);
        intent.putExtra(Key.NUMBER_A, numberA);
        intent.putExtra(Key.NUMBER_B, numberB);

        // Bật service
        startService(intent);

        // Tắt service
        // Intent intent = new Intent(this, CalculatorService.class);
        // topService(intent);
    }

}
