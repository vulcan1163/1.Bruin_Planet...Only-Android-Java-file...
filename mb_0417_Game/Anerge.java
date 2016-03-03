package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.GraphicObject;

public class Anerge extends GraphicObject {
	
	Bitmap anerge[];

	public Anerge(Bitmap bitmap) {
		super(bitmap);
		
		super.SetPosition(140, 33);
	
		
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap, m_x, m_y,null);
		
	}

	public void Update(long GameTime){
		
		
	
	}
}
