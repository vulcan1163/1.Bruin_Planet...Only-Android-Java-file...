package com.example.mb_0417_test;

import android.graphics.Canvas;

public class MenuBack extends GraphicObject{
	
	static final float SCROLL_SPEED_2 = 2.5f;
	private float m_scroll_2 = -2000 +480;

	public MenuBack() {
		super(AppManager.getInstance().getBitmap(R.drawable.icon2));
		
		super.SetPosition(200, (int)m_scroll_2);
	}
	
	public void Update(long GameTime){
		m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
		if(m_scroll_2 >= 0){
			m_scroll_2 = 0;
		}
		
		super.SetPosition(200, (int)m_scroll_2);
		
		AppManager.getInstance().m_MS.postInvalidate();
	}
	
	public void Draw(Canvas canvas){
		
		
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
		
		/*
		m_bitmap = Bitmap.createScaledBitmap(m_bitmap, img_width, img_height, false);
			*/
		
	
	}

	
	

}
