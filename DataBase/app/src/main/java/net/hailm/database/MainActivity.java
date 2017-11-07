package net.hailm.database;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private Button btnShowDialog;
    private Button btnDownload;
    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btnShowDialog = findViewById(R.id.btn_show_dialog);
        btnDownload = findViewById(R.id.btn_dowload);
        fileManager = new FileManager();
        requestPermissions();
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                fileManager.readExternalStorage();
            } else {
                String[] permissions = new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                requestPermissions(permissions, REQUEST_CODE_PERMISSION);
            }
        } else {
            fileManager.readExternalStorage();
        }
    }

    // Granted
    // Denied
    @TargetApi(Build.VERSION_CODES.M)
    private Boolean isPermissionGranted(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                fileManager.readExternalStorage();
            } else {
                Toast.makeText(this, "VUi lòng cấp quyền", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void registerListener() {
        btnShowDialog.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_dialog:
                openPaymentDialog();
                break;
            case R.id.btn_dowload:
                downloadImage();
                break;
            default:
                break;
        }
    }


    private void openPaymentDialog() {
        PaymentDialog dialog = new PaymentDialog(this);
        dialog.show();
    }

    private void downloadImage() {
        fileManager.downloadImage(this, "http://media.bongda.com.vn/files/hai.phan/2017/08/02/z-2313.jpg");
    }
}
