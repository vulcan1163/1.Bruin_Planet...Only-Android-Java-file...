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
	
	String myText1 = "������ �����մϴ�";
	String myText2 = "����";
	Stage_1 text;
	public String userName;
	
	private TextToSpeech myTTS;

	private SensorManager sm;
	private Display mDisplay; // ���÷��� ũ���� �������� �޾ƿɴϴ�.
	private WindowManager mWin; // ���÷����� context�� ���´�. (���� ��ȯ)
	private SensorEventListener accL; // ���ӵ�
	private Sensor accSensor; // ���ӵ�
	private Sensor mag; // ����
	private Sensor ori;
	public static float ACCY, ACCX;
	
	long LastRegenEnemy;
	float[] rotation = new float[9];  
	float[] result_data = new float[3];
	float[] mag_data = new float[3]; //���������͸� ������ �迭 ���� 
	float[] acc_data = new float[3]; //���ӵ������Ͱ��� �� �迭. ������ �������� ���ӵ��� ���ڰ��� ���� �־����.
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
		accL = (SensorEventListener) new Listener(); // ���ӵ� ���� ������ �ν��Ͻ�
		
		
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
		menu.add(0, 1, 0, "��������");
		menu.add(0, 2, 0, "��������");
		menu.add(0, 3, 0, "���� �ҷ�����");
		menu.add(0, 4, 0, "���� ����");
		menu.add(0, 6, 0, "ó�� ȭ������");
		
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

		sm.registerListener(accL, accSensor, SensorManager.SENSOR_DELAY_GAME); // ���ӵ�
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
		 Intent in = new Intent(MyActivity.this, EndGame.class); //����Ʈ ����(�� ��Ƽ��Ƽ, ���� ������ ��Ƽ��Ƽ)
	        
		 	
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
            stopService(new Intent("com.Example.service.test")); //����Ǹ� ��������� ������.

    }

	@Override
	public void onInit(int status) {
		
		
		
		myTTS.speak(userName+"�氩���ϴ� ������ �����մϴ�", TextToSpeech.QUEUE_FLUSH, null);
	
		
	}
	
	public void danger(){

		myTTS.speak(userName+"�� �����մϴ� ���� ����", TextToSpeech.QUEUE_ADD, null);
	}
	
}
