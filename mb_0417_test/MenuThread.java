package com.example.mb_0417_test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MenuThread extends Thread{
	
	private SurfaceHolder m_holder;
	private MenuSurfaceView m_MS;
	private boolean m_run = false;
	public boolean canRun = true;
	public boolean isWait = false;
	
	public MenuThread(SurfaceHolder surfaceHolder, MenuSurfaceView MS){
		
		m_holder = surfaceHolder;
		m_MS = MS;
		
	}
	
	public void StopThread(){
		canRun =false;
		synchronized(this){
			this.notify();
		}
	}
	
	public void PauseNResume(boolean wait){
		isWait = wait;
		synchronized(this){
			this.notify();
		}
	}
	
	public void setRunning(boolean run){
		m_run = run;
	}
	public void run(){
		Canvas canvas;
		
		while(m_run){
			
			canvas = null;
			
			try{
				
				canvas = m_holder.lockCanvas(null);
				
				synchronized(m_holder){
					
					m_MS.DrawChractor(canvas);
					m_MS.Update();
						
						
					
				}
			}finally{
				if(canvas != null)
					m_holder.unlockCanvasAndPost(canvas);
			}
		}
		
	}

}
