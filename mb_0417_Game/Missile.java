package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.mb_0417_test.SpriteAnimation;

public class Missile extends SpriteAnimation{
	
	Rect m_BoundBox = new Rect();

	
	public static final int STATE_NORMAR = 0;
	public static final int STATE_OUT = 1;
	public int state = STATE_NORMAR;
	
	public Missile(Bitmap bitmap) {
		super(bitmap);
	
	}

	
	

}
