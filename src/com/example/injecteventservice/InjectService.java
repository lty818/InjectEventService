package com.example.injecteventservice;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class InjectService extends Service {
	public static int TOUCH_X = 100;
	public static int TOUCH_Y = 200;
	
	public static long INTERVAL = 5000L; // 5 second

	Handler mHandler = new Handler();
	Runnable mInjectInputRunnable = new Runnable() {
		
		@Override
		public void run() {
			forceToTouch(TOUCH_X, TOUCH_Y);
			mHandler.postDelayed(mInjectInputRunnable, INTERVAL);
		}
	};
	
	@Override
	public void onStart(Intent intent, int startId) {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		if (outMetrics != null) {
			TOUCH_X = outMetrics.widthPixels / 8;
			TOUCH_Y = outMetrics.heightPixels / 7;
		}
		
		mHandler.postDelayed(mInjectInputRunnable, INTERVAL);

		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		mHandler.removeCallbacks(mInjectInputRunnable);
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	private void forceToTouch(int posX, int posY) {
		try {
			String X = String.valueOf(posX);
			String Y = String.valueOf(posY);
			Runtime.getRuntime().exec(String.format("/system/bin/input tap %s %s\n", X, Y));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
