package com.freeborrow.mb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	private CanvasThread canvasthread;
	
	public Panel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		canvasthread = new CanvasThread(getHolder(),this);
		setFocusable(true);
	}
	
	 public Panel(Context context) {
		   super(context);
		    getHolder().addCallback(this);
		    canvasthread = new CanvasThread(getHolder(), this);
		    setFocusable(true);

	    }

	 @Override
	 public void onDraw(Canvas canvas) {
		 Paint paint = new Paint();
		 Bitmap spidermant = BitmapFactory.decodeResource(getResources(), R.drawable.spidermant);
		 Bitmap ico = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		 canvas.drawBitmap(ico, 10, 10, null);
		 canvas.drawColor(Color.GREEN);
		 canvas.drawBitmap(spidermant, 15, 15, null);
		 canvas.drawCircle(50, 50, 20, paint);
	 }
	 

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder){
		canvasthread.setRunning(true);
		canvasthread.start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		boolean retry = true;
		canvasthread.setRunning(false);
		while (retry) {
			try {
				canvasthread.join();
				retry = false;
			} catch (InterruptedException e) {
				//we will try again and again
			}
		}
	}
	
}
