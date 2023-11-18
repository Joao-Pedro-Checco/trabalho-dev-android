package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pixelrunner.R;

public class Sky {
    private static final int GROUND_X_SPEED = 20;
    private int x = 0, y = 0;
    private int screenX, screenY;
    private Bitmap sky;

    public Sky(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY;

        sky = BitmapFactory.decodeResource(res, R.drawable.sky);
        sky = Bitmap.createScaledBitmap(sky, this.screenX, this.screenY, false);
    }

    public void update() {
        this.x -= GROUND_X_SPEED;

        if (this.x + this.sky.getWidth() < 0) {
            this.x = this.screenX;
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

    public Bitmap getBitmap() {
        return sky;
    }
}
