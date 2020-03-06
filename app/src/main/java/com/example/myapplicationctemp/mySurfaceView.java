package com.example.myapplicationctemp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    //boolean f = flag;
    MyDraw md;
    boolean checked=false;
    public mySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        md= new MyDraw(getHolder());
        md.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        md.setXY(event.getX(),event.getY());
        checked=true;
        return super.onTouchEvent(event);
    }

    class MyDraw extends Thread{
        private SurfaceHolder sh;
        float x=0;
        float y=0;
        float r=0;

        public MyDraw(SurfaceHolder h){
           this.sh=h;
        }

        public void setXY(float x,float y){
            this.x=x;
            this.y=y;
            this.r=0;
        }

        @Override
        public void run() {
            Paint paint=new Paint();
            paint.setColor(Color.YELLOW);

            while (true){
                Canvas c=sh.lockCanvas();
                c.drawColor(Color.BLUE);
                if (checked)
                {c.drawCircle(x,y,r,paint);
                    r+=5;}
                sh.unlockCanvasAndPost(c);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
