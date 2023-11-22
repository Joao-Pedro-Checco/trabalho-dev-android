package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.pixelrunner.R;

public class Sky {
    private static final int SKY_X_POS = 20;
    private int x = 0, y = 0;
    private int screenX, screenY;
    private Bitmap bitmap;

    public Sky(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY;

        bitmap = BitmapFactory.decodeResource(res, R.drawable.sky);
        bitmap = Bitmap.createScaledBitmap(bitmap, this.screenX, this.screenY, false);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.bitmap, this.x, this.y, paint);
    }

    public void update() {
        this.x -= SKY_X_POS;
        if (this.x + this.bitmap.getWidth() < 0) {
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
        return bitmap;
    }
}
