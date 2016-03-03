package com.example.mb_0417_test;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mb_0417_Game.Missile_Player;
import com.example.mb_0417_Intro.EndGame;
import com.example.mb_0417_Intro.Manu;
import com.example.mb_0417_Intro.Stage_1;

public class MyActivity extends Activity implements OnInitListener{
	
	String myText1 = "게임을 시작합니다";
	String myText2 = "레츠";
	Stage_1 text;
	public String userName;
	
	private TextToSpeech myTTS;

	private SensorManager sm;
	private Display mDisplay; // 디스플레이 크기의 정보값을 받아옵니다.
	private WindowManager mWin; // 디스플레이의 context를 얻어온다. (방향 전환)
	private SensorEventListener accL; // 가속도
	private Sensor accSensor; // 가속도
	private Sensor mag; // 마그
	private Sensor ori;
	public static float ACCY, ACCX;
	
	long LastRegenEnemy;
	float[] rotation = new float[9];  
	float[] result_data = new float[3];
	float[] mag_data = new float[3]; //센서데이터를 저장할 배열 생성 
	float[] acc_data = new float[3]; //가속도데이터값이 들어갈 배열. 각도를 뽑으려면 가속도와 지자계의 값이 있어야함.
	float[] m_result_data = new float[3];

	GameView GV;

	public MyActivity(){
		AppManager.getInstance().setActivity(this);
		text = new Stage_1();
		
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GV = new GameView(this, null);
		setContentView(R.layout.main);
	

		mWin = (WindowManager) getSystemService(WINDOW_SERVICE);

		mDisplay = mWin.getDefaultDisplay();

	

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mag = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		ori = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		accL = (SensorEventListener) new Listener(); // 가속도 센서 리스너 인스턴스
		
		
		 startService(new Intent("com.Example.service.test"));
		 Intent i = getIntent();
		 userName = i.getExtras().getString("name");
		 myTTS = new TextToSpeech(this, this);

		findViewById(R.id.ImageView05).setOnClickListener(ButtonClick);
		
		LastRegenEnemy = System.currentTimeMillis();
			
	}
	 Button.OnClickListener ButtonClick = new Button.OnClickListener(){
			
		 public void onClick(View view){
			 switch(view.getId()){
			 case R.id.ImageView05:


					if (System.currentTimeMillis() - LastRegenEnemy >= 500) {
						LastRegenEnemy = System.currentTimeMillis();
						
						 AppManager.getInstance().m_gamestate.m_pms.add(new Missile_Player(AppManager.getInstance().m_gamestate.px, AppManager.getInstance().m_gamestate.py));
							
						
						//SoundManager.getInstance().play(1);
						SoundManager.getInstance().m_SoundPool.play(1, 0.4f, 0.4f, 1, 0, 1);
					}
							 break;
	
			 }
		 }
	 };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "게임종료");
		menu.add(0, 2, 0, "게임저장");
		menu.add(0, 3, 0, "게임 불러오기");
		menu.add(0, 4, 0, "게임 정지");
		menu.add(0, 6, 0, "처음 화면으로");
		
		return true;
	} // end createoption item

	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case 1 :
			GV.StopGame();
			exit();
			finish();
			break;
		case 2 :			// Save Game
			break;
		case 3 : 			// Load Game
			break;
		case 4 :			// Pause / Resume Game
			break;
		case 6 :			//Background
			 startActivity(new Intent(MyActivity.this, Manu.class));
			 exit();
			 finish();
			 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
			break;
		}
		
		return true;
	} // end option item


	protected void onResume() {
		super.onResume();

		sm.registerListener(accL, accSensor, SensorManager.SENSOR_DELAY_GAME); // 가속도
		sm.registerListener(accL, mag, SensorManager.SENSOR_DELAY_GAME);
		sm.registerListener(accL, ori, SensorManager.SENSOR_DELAY_GAME);

	}
	

	protected void onPause() {
		super.onPause();
		if (sm != null) {
			sm.unregisterListener(accL);
		}
	}
	
	public void onStop(){
		AppManager.getInstance().m_gameview.m_Thread.interrupt();
		super.onStop();
	
	}
	

	protected void onDestroy() {
		super.onDestroy();
		if (sm != null) {
			sm.unregisterListener(accL);
		}
		
		myTTS.shutdown();
		
		exit();
		
	}
	
	
	
	private class Listener implements SensorEventListener {
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {


			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			
			ACCY = event.values[0]-6.5f;
			
			ACCX = event.values[1];
			
			}
			       

		}

	}
	
	public void EndGame(){
		 Intent in = new Intent(MyActivity.this, EndGame.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
	        
		 	
	        startActivity(in);
	        AppManager.getInstance().m_gameview.m_Thread.setRunning(false);

	        AppManager.getInstance().m_gameview.destroyDrawingCache();
	        
	      //  SoundManager.getInstance().m_SoundPool.release();
	        
	        finish();
	        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
	}
	/*
	public boolean onKeyDown(int keyCode, KeyEvent event){

        switch (keyCode){
        case KeyEvent.KEYCODE_BACK:
         exit();
        }
          return super.onKeyDown(keyCode, event);    

 }   */

        public void exit(){
            ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
            stopService(new Intent("com.Example.service.test")); //종료되면 배경음악이 꺼진다.

    }

	@Override
	public void onInit(int status) {
		
		
		
		myTTS.speak(userName+"방갑습니다 게임을 시작합니다", TextToSpeech.QUEUE_FLUSH, null);
	
		
	}
	
	public void danger(){

		myTTS.speak(userName+"님 위험합니다 위험 위험", TextToSpeech.QUEUE_ADD, null);
	}
	
}
