package net.hailm.database;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hai_l on 02/11/2017.
 */

public class FileManager {
    private static final String TAG = "FileManager";

    public FileManager() {

    }

    // Doc du lieu o vung nho dung chun
    // VUng nho External
    // Điều kiện: Ứng dựng phải được cấp quyền
    // Đối với API < 23 (Android 6.0) chạy bình thường
    // Nếu cấp quyền ở file điều hướng chương trình (AndroidMandifest)
    // API >=23 , xin quyền (request runtime)
    public void readExternalStorage() {
        // Lấy ra
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if (files[i].isDirectory()) {
                Log.d(TAG, name);
            }
            if (files[i].isFile()) {
                Log.d(TAG, name + " - " + files[i].length() / 1024 + "KB");
            }
        }

    }

    public void downloadImage(final Context context, final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = conn.getInputStream();

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();

                    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

                    String destPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/"
                            + Environment.DIRECTORY_DOWNLOADS
                            + "/"
                            + fileName;
                    File destFile = new File(destPath);
                    FileOutputStream outputStream = new FileOutputStream(destFile);

                    // Chuyển bitmap thành file lưu trên bộ nhớ
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                    outputStream.close();

                    Log.d(TAG, "Download success");
                    downloadSuccessNotification(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


        // Click  vào notification PendingIntent vs Intent implicit
    private void downloadSuccessNotification(Context context) {
        Notification notification = new Notification.Builder(context)
                .setContentTitle("Download")
                .setContentText("Download Sucess")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat.from(context).notify(0, notification);
    }
}
