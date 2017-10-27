package com.hailm.labintent2;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.text.SimpleDateFormat;

import static android.graphics.Color.WHITE;

/**
 * Created by hai_l on 14/10/2017.
 */

public class Clock {
    private static final int SIZE = 140;
    private static final int SIZEDATE = 100;
    private final int COLOR = WHITE;
    private float x;
    private float y;
    String value;
    int date;
    String ND;

    public Clock(int width, int height) {
        updateDateTime();
        x = width;
        y = height;

    }

    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(COLOR);
        paint.setStyle(Paint.Style.STROKE);

        paint.setTextSize(SIZE);
        canvas.drawText(value, x, y + 70, paint);

        paint.setTextSize(SIZEDATE);
        canvas.drawText(ND, x + 500, y + 200, paint);

    }

    public void updateDateTime() {
        long current = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        value = sdf.format(current);
        SimpleDateFormat sdd = new SimpleDateFormat("HH");


        date = Integer.parseInt(sdd.format(current));
        if (date >= 0 && date < 12) {
            ND = "Day";
        } else {
            ND = "Night";
        }


    }
}
