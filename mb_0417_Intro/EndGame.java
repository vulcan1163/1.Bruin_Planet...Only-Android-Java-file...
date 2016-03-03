package com.example.mb_0417_Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.R;

public class EndGame extends Activity {
	
	Button btn1, btn2;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //��Ʈ��ȭ���̹Ƿ� Ÿ��Ʋ�ٸ� ���ش�
        setContentView(R.layout.end);
        AppManager.getInstance().m_gameview.m_Thread.setRunning(true);
        
        btn1= (Button)findViewById(R.id.button1);
        btn2= (Button)findViewById(R.id.button2);
        
        btn1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.exit(0);
				return false;
			}
		});
        
        btn2.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent in = new Intent(EndGame.this, Manu.class); //����Ʈ ����(�� ��Ƽ��Ƽ, ���� ������ ��Ƽ��Ƽ)
		        
		        startActivity(in);
		        
		        finish();
		        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
				return false;
			}
		});
     
    }

}
