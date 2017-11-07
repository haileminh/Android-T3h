package net.hailm.storagelesson13.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hai_l on 04/11/2017.
 */

public class FileManager {
    private static final String TAG = "FileManager";

    public FileManager() {

    }

    public void calculateNumbers(Context context) {
        // Lấy ra đối tượng quản lí tài nguyên: AssetManager

        // Load ảnh assets
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("data.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(" ");
                int a = Integer.parseInt(data[0]);
                int b = Integer.parseInt(data[1]);
                int sum = a + b;
                Log.d(TAG, "calculateNumbers: " + sum);

                line = reader.readLine();
            }

            reader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
