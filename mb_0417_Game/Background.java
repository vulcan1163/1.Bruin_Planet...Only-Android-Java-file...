package com.example.mb_0417_Game;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.GraphicObject;
import com.example.mb_0417_test.R;

public class Background extends GraphicObject{
	
	int width, height;
	static final float SCROLL_SPEED = -2f;
	private float m_scroll=0;
	int count = 0;

	public Background() {
		
		super(AppManager.getInstance().getBitmap(R.drawable.stage_1_1));
		

		SetPosition( (int)m_scroll,0);
		width = GameView.width;
		height = GameView.height;
		
		}
	
	
		
		public void Update(long GameTime){
			
			m_scroll = m_scroll + SCROLL_SPEED;
			
			if(m_scroll <= -(m_bitmap.getWidth() - m_bitmap.getWidth()/2)){
				m_scroll =0;

			}
			
			SetPosition((int)m_scroll,0);
			
		}
		
	
		
	
	
	public int GetX(int x){
		
		return width = x;
	
	}
	
	public int GetY(int y){
		
		return height = y;
	
	
	
}
	
	public void Draw(Canvas canvas)
	{
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
		
		
		
	}
}
