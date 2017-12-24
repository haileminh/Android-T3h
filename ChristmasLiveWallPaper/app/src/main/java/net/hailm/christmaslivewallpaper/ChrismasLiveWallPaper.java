package net.hailm.christmaslivewallpaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

/**
 * Created by hai_l on 21/12/2017.
 */

public class ChrismasLiveWallPaper extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new ChrismasEngine();
    }

    private class ChrismasEngine extends Engine {
        private Handler handler;
        private Runnable runnable;
        private boolean visible;

        private Paint paint;
        private Canvas canvas;

        private int width;
        private int height;

        private Bitmap bgBitmap;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (visible) {
                        paintComponents();
                        handler.postDelayed(this, 10);
                    }
                }
            };

            visible = true;
            width = getResources().getDisplayMetrics().widthPixels;
            height = getResources().getDisplayMetrics().heightPixels;
            bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper);

            if (width < bgBitmap.getWidth() || height < bgBitmap.getHeight()) {
                bgBitmap = Bitmap.createScaledBitmap(bgBitmap, width, height, false);
            }

            handler.post(runnable);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                Toast.makeText(getApplicationContext(), "Move", Toast.LENGTH_SHORT).show();
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_SHORT).show();
            }

            super.onTouchEvent(event);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            this.visible = visible;
            if (visible) {
                handler.post(runnable);
            } else {
                handler.removeCallbacks(runnable);
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            visible = false;
            handler.removeCallbacks(runnable);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        private void paintComponents() {
            SurfaceHolder holder = getSurfaceHolder();
            if (holder == null) {
                return;
            }

            canvas = holder.lockCanvas();
            canvas.drawBitmap(bgBitmap, 0, 0, null);

            holder.unlockCanvasAndPost(canvas);
        }
    }
}
