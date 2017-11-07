package net.hailm.storagelesson13.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import net.hailm.storagelesson13.manager.FileManager;
import net.hailm.storagelesson13.manager.PrefManager;
import net.hailm.storagelesson13.R;

public class MainActivity extends AppCompatActivity {
    private FileManager fileManager;
    private Switch swtBackgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        registerPrefChange();
    }

    private void initializeComponents() {
        fileManager = new FileManager();
        fileManager.calculateNumbers(this);

        //
        swtBackgroundMusic = findViewById(R.id.swt_background_music);
        swtBackgroundMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PrefManager.setBackgroundMusicState(MainActivity.this, isChecked);
            }
        });

        boolean isOpen = PrefManager.getBackgroundMusicStatic(this);
        swtBackgroundMusic.setChecked(isOpen);
    }

    private void registerPrefChange() {
        SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);
        pref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
                if (key.equals(PrefManager.KEY_BACKGROUND_MUSIC)) {
                    Toast.makeText(MainActivity.this,
                            key + ":" + pref.getBoolean(key, false),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
