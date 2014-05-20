package com.example.videoplayerdemo;

import android.R.anim;
import android.R.string;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class FloatingServiceActivity extends Service {

	View view;
	WindowManager wm = null;
	WindowManager.LayoutParams wmLayoutParams = null;
	ImageButton playImageButton = null;
	int position, max;
	SeekBar proSeekBar;
	String TAG = "android.intent.action.VideoPlayerActivity";//广播标签
	boolean isPlay = false;
	@Override
	public void onCreate(){
		super.onCreate();
		view = LayoutInflater.from(this).inflate(R.layout.activity_videoplayer, null);
		playImageButton = (ImageButton)view.findViewById(R.id.mediacontroller_lock);
		proSeekBar = (SeekBar)view.findViewById(R.id.mediacontroller_seekbar);
		
		proSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(TAG);
				intent.putExtra("flag", "change");
				intent.putExtra("newposition", position);
				FloatingServiceActivity.this.sendBroadcast(intent);
				view.setVisibility(View.GONE);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (fromUser) {
					position = progress;
				}
			}
		});
		
		playImageButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(TAG);
				if (isPlay) {
					intent.putExtra("flag", "pause");
					isPlay = false;
					playImageButton.setImageDrawable(getResources().getDrawable((R.drawable.mediacontroller_pause01)));
				}else {
					intent.putExtra("flag", "play");
					isPlay = true;
					playImageButton.setImageDrawable(getResources().getDrawable((R.drawable.mediacontroller_play01)));
					//动画透明淡出效果未实现？
					AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
					anim.setDuration(2000);
					view.startAnimation(anim);
					view.setVisibility(View.GONE);
				}
				FloatingServiceActivity.this.sendBroadcast(intent);
				//view.setVisibility(View.GONE);
			
			}
		
		});
		
		createView();
	}
	
	private void createView() {
		// TODO Auto-generated method stub
		wm = (WindowManager)getApplication().getSystemService("window");
		wmLayoutParams = new WindowManager.LayoutParams();
		wmLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
		wmLayoutParams.flags|= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		wmLayoutParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
		
		wmLayoutParams.x = 0;
		wmLayoutParams.y = 0;
		wmLayoutParams.format = 1;
		wmLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
		wm.addView(view, wmLayoutParams);
		
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				view.setVisibility(View.GONE);
				return true;
			}
		});		
		
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags, int startId){
		// TODO Auto-generated method stub
        position = intent.getIntExtra("position", 0);
        max = intent.getIntExtra("max", 0);
        boolean visable = intent.getBooleanExtra("visable", false);
        proSeekBar.setMax(max);
        proSeekBar.setProgress(position);
        if(visable)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
        return super.onStartCommand(intent, flags, startId);
	}
	
    @Override  
    public void onDestroy() {     
        wm.removeView(view);  
        super.onDestroy();  
    }  
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}	
 
}
