package com.example.pixelrunner.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.pixelrunner.Sprite.Ground;
import com.example.pixelrunner.Sprite.Player;
import com.example.pixelrunner.Sprite.Sky;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Ground ground1, ground2;
    private Sky sky;
    private Paint paint;
    private Player player;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        sky = new Sky(this.screenX, this.screenY, getResources());
        ground1 = new Ground(this.screenX, this.screenY, getResources());
        ground2 = new Ground(this.screenX, this.screenY, getResources());
        player = new Player(screenY, getResources());

        ground2.setX(this.screenX);

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

    protected void update() {
        ground1.update();
        ground2.update();
        player.update();
    }

    protected void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            sky.draw(canvas, paint);
            ground1.draw(canvas, paint);
            ground2.draw(canvas, paint);
            player.draw(canvas, paint);

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump();
            Log.d("toque", "pulou");
        }

        return true;
    }
}
