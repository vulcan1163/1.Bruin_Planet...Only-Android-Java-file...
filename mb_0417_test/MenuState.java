package com.example.mb_0417_test;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MenuState implements IState{

	public MenuBack m_back;
	
	public void Init() {
		m_back = new MenuBack();
		
	}

	
	public void Destroy() {
		
		
	}

	
	public void Update() {
		long GameTime = System.currentTimeMillis();
		m_back.Update(GameTime);
		
	}

	
	public void Render(Canvas canvas) {
		m_back.Draw(canvas);
		
	}

	
	public boolean onKeyDown(int keycode, KeyEvent event) {
		return true;
	}

	
	public boolean onTouchEvent(MotionEvent event) {
		
		
		
			return true;
	}

}
