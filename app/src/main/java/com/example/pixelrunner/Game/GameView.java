package com.example.pixelrunner.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.pixelrunner.Sprite.Ground;
import com.example.pixelrunner.Sprite.Sky;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Ground ground;
    private Sky sky1, sky2;
    private Paint paint;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        sky1 = new Sky(this.screenX, this.screenY, getResources());
        sky2 = new Sky(this.screenX, this.screenY, getResources());
        ground = new Ground(this.screenX, this.screenY, getResources());

        sky2.setX(this.screenX);

        paint = new Paint();
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        sky1.update();
        sky2.update();
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(sky1.getBitmap(), sky1.getX(), sky1.getY(), paint);
            canvas.drawBitmap(sky2.getBitmap(), sky2.getX(), sky2.getY(), paint);
            canvas.drawBitmap(ground.getBitmap(), ground.getX(), ground.getY(), paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
