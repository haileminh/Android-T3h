package com.hailm.testcustomview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by hai_l on 23/09/2017.
 */

public class NumberArt {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int SIZE = 196;

    private int x;
    private int y;
    private int orient;
    private int color;
    private int value;

    public NumberArt(int x, int y, int orient, int color, int value) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        this.color = color;
        this.value = value;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setTextSize(SIZE);
        canvas.drawText(String.valueOf(value), x, y, paint);
    }

    public void move(int width, int height) {
        switch (orient) {
            case LEFT:
                x--;
                if (x <= 0) {
                    orient = RIGHT;
                }
                break;
            case UP:
                y--;
                if (y <= SIZE) {
                    orient = DOWN;
                }
                break;
            case RIGHT:
                x++;
                if (x >= width- SIZE) {
                    orient = LEFT;
                }
                break;
            case DOWN:
                y++;
                if (y >= height) {
                    orient = UP;
                }
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
