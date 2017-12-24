package net.hailm.camerademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final int REQUES_CODE_PERMISSION = 1000;
    private TextureView ttvCamera;
    private Camera camera;
    private boolean hasCameraPermission;

    //    private int widthCamera;
//    private int heightCamera;
    private int width, height;
    private boolean isReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isPermissionGranted(Manifest.permission.CAMERA)) {
                hasCameraPermission = false;
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUES_CODE_PERMISSION);
            } else {
                hasCameraPermission = true;
            }
        } else {
            hasCameraPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUES_CODE_PERMISSION) {
            if (grantResults.length == 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
                hasCameraPermission = false;
                Toast.makeText(this, "Require camera permission", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                hasCameraPermission = true;
                openCamera();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isPermissionGranted(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void initializeComponents() {
        ttvCamera = findViewById(R.id.ttv_camera);
        ttvCamera.setSurfaceTextureListener(this);
        ttvCamera.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isReady) {
                    width = ttvCamera.getWidth();
                    height = ttvCamera.getHeight();
                    isReady = true;
                    if (hasCameraPermission) {
                        openCamera();
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (hasCameraPermission && isReady) {
            openCamera();
        }

    }

    @Override
    protected void onPause() {
        closeCamera();
        super.onPause();
    }

    private void openCamera() {
//      camera = Camera.open(); // tương tự  camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK); // mở camra sau
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK); // mở camra sau

        Camera.Parameters parameters = camera.getParameters();
        // Config
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        Camera.Size bestSize = getBestSize(sizes);

        parameters.setPreviewSize(bestSize.width, bestSize.height);
        parameters.setPictureSize(bestSize.width, bestSize.height);

        sizes = parameters.getSupportedPictureSizes();
        bestSize = getBestSize(sizes);
        parameters.setPictureSize(bestSize.width, bestSize.height);

        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);


    }

    private Camera.Size getBestSize(List<Camera.Size> sizes) {
        Camera.Size bestSize = null;
        float ratio = width > height ? (float) width / height : (float) height / width;
        float minRatio = 0.01f;
        for (Camera.Size size : sizes) {
            int w = size.width;
            int h = size.height;
            float r = w >= h ? (float) w / h : (float) h / w;

            if (Math.abs(ratio - r) <= minRatio) {
                bestSize = size;
                minRatio = Math.abs(ratio - r);
            }
        }

        if (bestSize != null) {
            return bestSize;
        }

        int minMultiple = Integer.MAX_VALUE;
        int multiple = width * height;
        for (Camera.Size size : sizes) {
            int w = size.width;
            int h = size.height;
            int m = w * h;

            if (Math.abs(multiple - m) <= minMultiple) {
                bestSize = size;
                minMultiple = Math.abs(multiple - m);
            }
        }

        return bestSize;
    }

    private void closeCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public void captureImage() {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                // convert bitmap to file
                // TODO
                // Permission
            }
        });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (hasCameraPermission) {
            return;
        }

        try {
            camera.setPreviewTexture(ttvCamera.getSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {

            }
        });
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
