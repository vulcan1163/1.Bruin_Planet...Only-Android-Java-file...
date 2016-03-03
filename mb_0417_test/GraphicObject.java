package com.example.mb_0417_test;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
	public Bitmap m_bitmap;
	public int m_x;
	public int m_y;
	public int s_width, s_height;
	public int img_width, img_height;

	
	public GraphicObject(Bitmap bitmap){
		
		m_bitmap = bitmap;
		
		m_x=0;
		m_y=0;
		
		s_width = 100;
		s_height = 100;
		
		
	}
	

	public void SetPosition(int x, int y){
		m_x = x;
		m_y = y;
	}
	
	public void Draw(Canvas canvas){
		
		canvas = new Canvas();
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
		
		/*
		m_bitmap = Bitmap.createScaledBitmap(m_bitmap, img_width, img_height, false);
			*/
		
	
	}
	
	public void Scale(int width, int height){
		
	this.s_width = width;
	this.s_height = height;
		
		
	}
	
	
	public int GetX(){
		return m_x;
	}
	public int Gety(){
		return m_y;
	}

}
