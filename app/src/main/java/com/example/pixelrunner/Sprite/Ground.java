package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pixelrunner.R;

public class Ground {
    private int x = 0, y = 0;
    private int screenX, screenY;
    private Bitmap ground;

    public Ground(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY / 2;

        this.y = this.screenY + (this.screenY / 2);

        ground = BitmapFactory.decodeResource(res, R.drawable.ground);
        ground = Bitmap.createScaledBitmap(ground, this.screenX, this.screenY, false);
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
        return ground;
    }
}
