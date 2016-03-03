package com.example.mb_0417_Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.MyActivity;
import com.example.mb_0417_test.R;

public class Stage_1 extends Activity{
	
	Button btn1, btn2;
	EditText ed1;
	public String userName;
	
	   protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
	        setContentView(R.layout.stage_1);
	        

	        AppManager.getInstance().setActivity(new MyActivity());
	        
	        btn1 = (Button)findViewById(R.id.btn1);
	        btn2 = (Button)findViewById(R.id.btn2);
	        ed1 = (EditText)findViewById(R.id.edt1);
	        
	        btn1.setOnClickListener(new View.OnClickListener() {
				
				
				public void onClick(View v) {
					Intent i = new Intent(Stage_1.this, MyActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
			        
					userName = ed1.getText().toString();
					i.putExtra("name", userName);
					
			        startActivity(i);
			        finish();
			        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); 
					
					
				}
			});
	        
	        btn2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					System.exit(0);	
				}
			});
	        
	       
	     
	    }
	 
	     
	  
	    public void onBackPressed(){
	        super.onBackPressed();
	
	    }

}
