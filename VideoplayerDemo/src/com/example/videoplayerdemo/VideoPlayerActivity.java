package com.example.videoplayerdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;


public class VideoPlayerActivity extends Activity implements SurfaceHolder.Callback{

	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	static MediaPlayer mediaPlayer;
	Boolean isPlay = false;
	private Intent floatwindow = null;
	static String TAG = "android.intent.action.VideoPlayerActivity";//广播标签
	MyBroadcastReceiver mr;
	String filepath = Environment.getExternalStorageDirectory()+"/keyshare/mp.mp4";
	String filepathmpg = "sdcard/beyond.3gp";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置屏幕方向，是否有标题栏，是否全屏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        setContentView(R.layout.activity_main);
        //初始化类对象
        
		Log.e("surfaceCreated", "test0");
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
       
        
        floatwindow = new Intent();
        floatwindow.setClass(VideoPlayerActivity.this, FloatingServiceActivity.class);
        mr = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TAG);
        this.registerReceiver(mr, intentFilter);
        Intent intent = getIntent();
		Log.e("surfaceCreated", "test3");
    }

	@Override
	public void onDestroy(){
		stopService(floatwindow);
		this.unregisterReceiver(mr);
		if (this.mediaPlayer != null) {
			this.mediaPlayer.release();
			mediaPlayer = null;
		}
		super.onDestroy();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int position = this.mediaPlayer.getCurrentPosition();
		floatwindow.putExtra("position", position);
		floatwindow.putExtra("max", mediaPlayer.getDuration());
		floatwindow.putExtra("visable", true);
		Log.e("Touch", "test0");
		startService(floatwindow);
		Log.e("Touch", "test1");
		return super.onTouchEvent(event);
	}
	//播放视频函数
	public void palyVideo(String path){
		if (mediaPlayer.isPlaying() == true) {
			mediaPlayer.reset();
		}
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDisplay(surfaceHolder);
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		mediaPlayer.start();
	}
	
	public static class MyBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String actionString = intent.getAction();
			if (actionString.equals(TAG)) {
				String flagString = intent.getStringExtra("flag");
				if (flagString.equals("play")) {
					if (!mediaPlayer.isPlaying()) {
						mediaPlayer.start();
					}
				}else if(flagString.equals("pause")){
					if (mediaPlayer.isPlaying()) {
						mediaPlayer.pause();
					}
				}else if (flagString.equals("change")) {
					int pos = intent.getIntExtra("newposition", 0);
					if (mediaPlayer.isPlaying()) {
						mediaPlayer.pause();
					}
					mediaPlayer.seekTo(pos);
					mediaPlayer.start();
				}else {
					//VideoPlayerActivity.this.finish();
				}
			}
		}
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.e("surfaceCreated", "test1");
		 	mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        mediaPlayer.setDisplay(surfaceHolder);
	        
		Log.e("surfaceCreated", "test11");
	        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mediaPlayer.stop();
					VideoPlayerActivity.this.finish();
				}
			});
	        
		Log.e("surfaceCreated", "test12");
		//mediaPlayer.setDisplay(holder);
		try {
		Log.e("surfaceCreated", "test13");
			mediaPlayer.setDataSource(filepathmpg);
		Log.e("surfaceCreated", "test14");
			mediaPlayer.prepare();
		Log.e("surfaceCreated", "test15");
			mediaPlayer.start();
		Log.e("surfaceCreated", "test2");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}	
 
}
