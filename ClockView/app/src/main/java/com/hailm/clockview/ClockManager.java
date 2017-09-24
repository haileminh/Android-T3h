package com.hailm.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;

/**
 * Created by hai_l on 23/09/2017.
 */

public class ClockManager {
    public static final int SIZE = 80;
    private int width;
    private int height;
    private String result, date;

    public ClockManager(Context context) {
        initalize(context);
        updateDateTime();
    }

    public void drawBg(Canvas canvas, Paint paint) {

        paint.setColor(Color.parseColor("##F97E76"));
        canvas.drawCircle(width / 2, height / 2, width * 3 / 8, paint);
    }

    public void drawText(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(SIZE);
        float length = paint.measureText(result) / 2;
        canvas.drawText(result, width / 2 - length, height / 2 - 100, paint);

        paint.setTextSize(50);
        float lenghtDate = paint.measureText(date) / 2;
        canvas.drawText(date, width / 2 - lenghtDate, height / 2 + 20, paint);
    }

    private void initalize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    public void updateDateTime() {
        long current = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
        result = simpleDateFormat.format(current);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(current);
    }

}
