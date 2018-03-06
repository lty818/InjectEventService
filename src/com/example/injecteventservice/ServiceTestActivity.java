package com.example.injecteventservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServiceTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_starter);
		
		Button target = (Button) findViewById(R.id.target_button);
		target.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Target Button was pressed by hidden service!", Toast.LENGTH_SHORT).show();
			}
		});
		
		this.startService(new Intent(this, InjectService.class));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			Toast.makeText(this, "Touch Down occured at (" + event.getX() + ", " + event.getY() + ")", Toast.LENGTH_SHORT).show();
		return super.onTouchEvent(event);
	}
}
