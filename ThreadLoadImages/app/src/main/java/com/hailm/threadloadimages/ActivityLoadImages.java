package com.hailm.threadloadimages;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hai_l on 07/10/2017.
 */

public class ActivityLoadImages extends Activity implements View.OnClickListener {
    public static final int MSG_UPDATE_IMAGE = 100;
    public static final int MSG_UPDATE_1 = 100;
    public static final int MSG_UPDATE_2 = 101;
    public static final int MSG_UPDATE_3 = 102;
    public static final int MSG_UPDATE_4 = 103;
    public static final int MSG_UPDATE_5 = 104;

    private Button btnLoad;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView imgFull;
    private Handler handler;

    private String link1;
    private String link2;
    private String link3;
    private String link4;
    private String link5;
    private Bitmap bImage1;
    private Bitmap bImage2;
    private Bitmap bImage3;
    private Bitmap bImage4;
    private Bitmap bImage5;

    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        initialiazeComponents();
        registerLisntener();
    }

    private void initialiazeComponents() {
        link1 = "http://media.thethaovanhoa.vn/Upload/1ULa3urWs9Lc3ZdKw10L3Q/files/2017/06/Mung%202/14792088093755.jpg";
        link2 = "http://media.bongda.com.vn/files/hai.phan/2017/08/02/z-2313.jpg";
        link3 = "http://www3.pictures.zimbio.com/gi/FC+Barcelona+v+Celtic+FC+UEFA+Champions+League+Uf6DKz5F14gx.jpg";
        link4 = "https://cnsoccpr.turbobytes.net/images/blog/messi.jpg";
        link5 = "http://www1.pictures.zimbio.com/gi/Lionel+Messi+Barcelona+FC+v+Malaga+CF+Copa+k9GDlBSt6j-l.jpg";
        btnLoad = findViewById(R.id.btn_load);
        img1 = findViewById(R.id.iv_image1);
        img2 = findViewById(R.id.iv_image2);
        img3 = findViewById(R.id.iv_image3);
        img4 = findViewById(R.id.iv_image4);
        img5 = findViewById(R.id.iv_image5);
        imgFull = findViewById(R.id.iv_image_full);

        dialog = new Dialog(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MSG_UPDATE_1:
                        bImage1 = (Bitmap) msg.obj;
                        img1.setImageBitmap(bImage1);
                        break;
                    case MSG_UPDATE_2:
                        bImage2 = (Bitmap) msg.obj;
                        img2.setImageBitmap(bImage2);
                        break;
                    case MSG_UPDATE_3:
                        bImage3 = (Bitmap) msg.obj;
                        img3.setImageBitmap(bImage3);
                        break;
                    case MSG_UPDATE_4:
                        bImage4 = (Bitmap) msg.obj;
                        img4.setImageBitmap(bImage4);
                        break;
                    case MSG_UPDATE_5:
                        bImage5 = (Bitmap) msg.obj;
                        img5.setImageBitmap(bImage5);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void registerLisntener() {
        btnLoad.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load:
                loadImages();
                btnLoad.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_image1:
                showDialog(bImage1);
            case R.id.iv_image2:
                showDialog(bImage2);
            case R.id.iv_image3:
                showDialog(bImage3);
            case R.id.iv_image4:
                showDialog(bImage4);
            case R.id.iv_image5:
                showDialog(bImage5);
            default:
                break;
        }
    }


    private void loadImages() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getLinkDownLoad(link1, MSG_UPDATE_1);
                getLinkDownLoad(link2, MSG_UPDATE_2);
                getLinkDownLoad(link3, MSG_UPDATE_3);
                getLinkDownLoad(link4, MSG_UPDATE_4);
                getLinkDownLoad(link5, MSG_UPDATE_5);
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void getLinkDownLoad(String link, int pos) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conn.getInputStream();

            Bitmap image = BitmapFactory.decodeStream(inputStream);

            Message msg = new Message();
            msg.what = pos;
            msg.obj = image;

            handler.sendMessage(msg);

            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialog(Bitmap img) {
        dialog.setContentView(R.layout.activity_dialog);
        dialog.setTitle("Dialog");

        imgFull = dialog.findViewById(R.id.iv_image_full);
        imgFull.setImageBitmap(img);
        dialog.show();
    }
}
