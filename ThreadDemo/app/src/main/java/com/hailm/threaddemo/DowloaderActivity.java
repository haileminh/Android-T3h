package com.hailm.threaddemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.graphics.BitmapCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hai_l on 05/10/2017.
 */

public class DowloaderActivity extends Activity implements View.OnClickListener {
    private static final int MESSAGE_UPDATE_NUMBER = 100;
    private static final int MESSAGE_UPDATE_DONE = 101;
    private static final int MESSAGE_UPDATE_IMAGE = 102;

    private ProgressBar pgbDownload;
    private Button btnDowload;
    private TextView tvNumber;
    private Handler handler;
    private ImageView imgImgae;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dowloader);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        pgbDownload = findViewById(R.id.pgb_download);
        btnDowload = findViewById(R.id.btn_download);
        tvNumber = findViewById(R.id.tv_number);
        imgImgae = findViewById(R.id.img_image);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MESSAGE_UPDATE_NUMBER:
                        int value = msg.arg1;
                        tvNumber.setText(String.valueOf(value));
                        break;
                    case MESSAGE_UPDATE_DONE:
                        Toast.makeText(DowloaderActivity.this, "DONE", Toast.LENGTH_SHORT).show();
                        // Thực hiện một công việc sau một quang thời gian
//                       handler.postDelayed(r, ms)
                    case MESSAGE_UPDATE_IMAGE:
                        Bitmap image = (Bitmap) msg.obj;
                        imgImgae.setImageBitmap(image);
                        pgbDownload.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void registerListener() {
        btnDowload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                pgbDownload.setVisibility(View.VISIBLE);
              //  dowloadData();
                dowloadImage();
                break;
            default:
                break;
        }
    }

    private void dowloadData() {
        // Thread

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int value = 0;
                while (value < 100) {
                    value++;
                    // Gửi yêu cầu đến handler
                    // Nhờ Handler xử lý tiếp
                    Message msg = new Message();
                    msg.what = MESSAGE_UPDATE_NUMBER;
                    msg.arg1 = value;
                    msg.setTarget(handler);
                    msg.sendToTarget();

                    pgbDownload.setProgress(value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                Message msg = new Message();
//                msg.what = MESSAGE_UPDATE_DONE;
//                handler.sendMessage(msg);

                handler.sendEmptyMessage(MESSAGE_UPDATE_DONE);

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void dowloadImage() {
        // Khi làm việc INTERNET bắt buộc phải làm viêc trên 1 thread khác
        //
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String link = "http://krw-img.s3.amazonaws.com/Aisha.png";
                    URL url = new URL(link);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream input = conn.getInputStream();

                    // Download
                    // Ảnh tròng Android được mô tả bằng đối tượng Bitmap
                    Bitmap image = BitmapFactory.decodeStream(input);

                    //Hiện thị ảnh
                    //imgImgae.setImageBitmap(image);
                    Message msg = new Message();
                    msg.what = MESSAGE_UPDATE_IMAGE;
                    msg.obj = image;
                    handler.sendMessage(msg);
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
}
