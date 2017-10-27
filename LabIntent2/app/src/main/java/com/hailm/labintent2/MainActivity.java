package com.hailm.labintent2;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CAMERA_APP = 100;  //REQUEST_CODE + màn hình
    private static final int REQUEST_CODE_CONTACT_APP = 101;

    private RelativeLayout btnDialer;
    private RelativeLayout btnMusic;
    private RelativeLayout btnMessaging;
    private RelativeLayout btnContact;
    private RelativeLayout btnCamera;
    private RelativeLayout btnGallery;
    private LinearLayout btnClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btnClock = (LinearLayout) findViewById(R.id.btn_clock);
        btnDialer = (RelativeLayout) findViewById(R.id.btn_dialer);
        btnMessaging = (RelativeLayout) findViewById(R.id.btn_messaging);
        btnMusic = (RelativeLayout) findViewById(R.id.btn_music);
        btnCamera = (RelativeLayout) findViewById(R.id.btn_camera);
        btnContact = (RelativeLayout) findViewById(R.id.btn_contact);
        btnGallery = (RelativeLayout) findViewById(R.id.btn_gallery);
    }

    private void registerListener() {
        btnClock.setOnClickListener(this);
        btnDialer.setOnClickListener(this);
        btnMessaging.setOnClickListener(this);
        btnMusic.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clock:
                break;

            case R.id.btn_dialer:
                showDialerScreen();
                break;

            case R.id.btn_messaging:
                showMessageApp();
                break;

            case R.id.btn_contact:
                showContactApp();
                break;

            case R.id.btn_camera:
                showCameraApp();
                break;

            case R.id.btn_music:
                showMusicApp();
                break;

            case R.id.btn_gallery:
                showImage();
                break;

            default:
                break;
        }
    }

    private void showImage() {
        Intent imageIntent = new Intent(this, ImageActivity.class);
        startActivity(imageIntent);
    }

    private void showMusicApp() {
        Intent musicAppIntent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        startActivity(musicAppIntent);
    }

    private void showCameraApp() {
        Intent cameraAppIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraAppIntent, REQUEST_CODE_CAMERA_APP);
    }

    private void showContactApp() {
        Intent contactAppIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactAppIntent, REQUEST_CODE_CONTACT_APP);
    }

    private void showMessageApp() {
        Intent messageIntent = new Intent(Intent.ACTION_VIEW);
        messageIntent.setData(Uri.parse("sms: "));
        startActivity(messageIntent);
    }

    private void showDialerScreen() {
        Intent dialerIntent = new Intent(MainActivity.this, DialerActivity.class);
        startActivity(dialerIntent);
    }
}
