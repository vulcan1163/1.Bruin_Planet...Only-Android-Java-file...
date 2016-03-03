package com.example.mb_0417_test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service{

	public MediaPlayer mp;
	
	public IBinder onBind(Intent intent) {
		
		return null;
		
		
	}
	
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		
		mp = MediaPlayer.create(this, R.raw.stage_3);
		mp.setLooping(true);
		mp.setVolume(0.8f, 0.8f);
		mp.start();
	}
	
	public void onDestroy(){
		super.onDestroy();
		mp.stop();
		
	}

}
