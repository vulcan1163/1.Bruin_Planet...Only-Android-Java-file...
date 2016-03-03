package com.example.mb_0417_Intro;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SoundManager;


public class Manu extends Activity {

	  ImageView image1, image2,image3;
	  Animation animation1,animation2,animation3;
	  private int count =0;
	 private int i=1;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
	      
	        setContentView(R.layout.manu);

	        animation1 = AnimationUtils.loadAnimation(this, R.anim.frame_animation);
	        animation2 = AnimationUtils.loadAnimation(this, R.anim.frame_animation_2);
	        animation1.setDuration(2000);
	        animation1.setFillBefore(true);
	        animation1.setFillAfter(true);
	        animation2.setDuration(2000);
	        animation2.setFillBefore(true);
	        animation2.setFillAfter(true);

	      findViewById(R.id.ImageView01).setOnClickListener(ButtonClick);
	      findViewById(R.id.ImageView02).setOnClickListener(ButtonClick);
	      findViewById(R.id.ImageView03).setOnClickListener(ButtonClick);
	      findViewById(R.id.ImageView04).setOnClickListener(ButtonClick);
	      startService(new Intent("com.Example.service.test_2"));
	      
	      h.sendEmptyMessage(2000);
	   
	    }
	 Handler h = new Handler(){

			public void handleMessage(Message msg){

				if(i == 3){
				i=1;
				h.sendEmptyMessage(0);
				}

				else if (i == 1){
					
					
					
			        image1 = (ImageView) findViewById(R.id.image01);
			        image1.setImageResource(R.drawable.space_back_5_1);
					image1.startAnimation(animation1);
					
					h.sendEmptyMessageDelayed(0, 2000); // ms단위 딜레이 함수
					
					i++;
					
				}
				
				else if (i == 2){
					
					
					
			        image1 = (ImageView) findViewById(R.id.image01);
			        image1.setImageResource(R.drawable.space_back_5_1);
					image1.startAnimation(animation2);
					
					h.sendEmptyMessageDelayed(0, 2000); // ms단위 딜레이 함수
					
					i++;
					
				}
				
		
			}
		};
		
	 Button.OnClickListener ButtonClick = new Button.OnClickListener(){
		
		 public void onClick(View view){
			 switch(view.getId()){
			 case R.id.ImageView01:
				 startActivity(new Intent(Manu.this, Stage_1.class));
				 
				 finish();
				 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
				 break;
			 case R.id.ImageView02:
				 break;
			 case R.id.ImageView03:
				 break;
			 case R.id.ImageView04:
				 exit();
				 finish();
				 break;
	
			 }
		 }
	 };
		protected void onDestroy() {
			super.onDestroy();
			
			
			exit();
			
		}
		
	 public void exit(){
         ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
         am.restartPackage(getPackageName());
         stopService(new Intent("com.Example.service.test_2")); //종료되면 배경음악이 꺼진다.

 }
	 
}

