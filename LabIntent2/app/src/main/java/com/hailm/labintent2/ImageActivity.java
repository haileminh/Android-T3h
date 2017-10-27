package com.hailm.labintent2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by hai_l on 14/10/2017.
 */

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_IMAGES = 100;
    private ImageView btnBack;
    private ImageView btnImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_image);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnImage = (ImageView) findViewById(R.id.btn_Image);
    }

    private void registerListener() {
        btnBack.setOnClickListener(this);
        btnImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                backMain();
                break;

            case R.id.btn_Image:
                selectImage();
                break;

            default:
                break;
        }
    }

    private void selectImage() {
        Intent imageIntent = new Intent(Intent.ACTION_PICK);
        imageIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(imageIntent, "Select Picture"), REQUEST_CODE_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_IMAGES:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null && data.getData() != null) {

//                        Bitmap image = (Bitmap) data.getExtras().get("data"); // mac dinh
//                        btnImage.setImageBitmap(image);

                        try {
                            Uri uri = data.getData();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            btnImage.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

    private void backMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
