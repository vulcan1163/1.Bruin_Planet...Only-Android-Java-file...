package com.example.mb_0417_test;

import java.text.AttributedCharacterIterator.Attribute;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.mb_0417_Game.Background;

public class MenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


	public MenuThread m_Thread;
	private IState m_state;
	private GraphicObject m_image;
	Background Back;
	public static int width;
	public static int height;

	
	public static float t_x, t_y;
	
	public MenuSurfaceView(Context context, AttributeSet attr) {
		super(context, attr);
		
		setFocusable(true);
		Back = new Background();
		
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		width = display.getWidth();
		height = display.getHeight();
		
		
		
		SoundManager.getInstance().Init(context);
		
		
		AppManager.getInstance().setMenuSurfaceView(this);
		AppManager.getInstance().setResources(getResources());
		
		getHolder().addCallback(this);
		
		ChangedGameState(new MenuState());
		
		m_Thread = new MenuThread(getHolder(), this);
		
	}
	

	public void DrawChractor(Canvas canvas){
		
		m_state.Render(canvas);
		
	}
	
	public void Update(){
		
		m_state.Update();
		postInvalidate();
		
	}
	
	public void ChangedGameState(IState state){
		
		if(m_state != null)
			m_state.Destroy();
			state.Init();
			m_state=state;
		
	}
	
	public void StopGame(){
		m_Thread.StopThread();
		
	}
	
	public void PauseGame(){
		m_Thread.PauseNResume(true);
	}
	
	public void ResumeGame(){
		m_Thread.PauseNResume(false);
	}
	

	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	
	}


	public void surfaceCreated(SurfaceHolder arg0) {
		m_Thread.setRunning(true);
		m_Thread.start();
	
	}


	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		
		m_Thread.setRunning(false);
		while(retry){
			try{
				
			m_Thread.join();
			retry = false;
			
		}catch(InterruptedException e){}
	}
	}
	
	public boolean onTouchEvent(MotionEvent event){
	
		return m_state.onTouchEvent(event);
		
	}

}
