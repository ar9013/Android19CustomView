package com.javaclass.anima.android19customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anima on 2016/7/30.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Paint paint;
    private int x,y,dy;
    private boolean isInit;
    private Bitmap bg,pad;

    public MySurfaceView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setColor(Color.YELLOW);

        x=y=100;
        dy =12;

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.bg0);
        pad = BitmapFactory.decodeResource(getResources(),R.drawable.pad);

    }

    void drawCanvas(){
        Canvas canvas;

        if(!isInit){
            canvas = holder.lockCanvas();
            canvas.drawBitmap(bg,0,0,null);
            canvas.drawBitmap(pad,350,350,null);
            isInit = true;
        }else{
            canvas = holder.lockCanvas(new Rect(x-50,y-50-dy,x+50,y+50));
            canvas.drawBitmap(bg,0,0,null);
        }
        canvas.drawCircle(x,y,50,paint);

        holder.unlockCanvasAndPost(canvas);
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 40; i++) {
                y += dy;
                drawCanvas();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // drawCanvas();
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
