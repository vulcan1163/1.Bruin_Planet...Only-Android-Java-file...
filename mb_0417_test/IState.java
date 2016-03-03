package com.example.mb_0417_test;


import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {

	public void Init();
	public void Destroy();
	public void Update();
	public void Render(Canvas canvas);
	public boolean onKeyDown(int keycode, KeyEvent event);
	public boolean onTouchEvent(MotionEvent event);
}
