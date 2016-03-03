package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.SpriteAnimation;

public class Enemy extends SpriteAnimation{

	Rect m_BoundBox = new Rect();
	

	public static final int STATE_NORMAR = 0;
	public static final int STATE_OUT = 1;
	public int state = STATE_NORMAR;
	
	public static final int MOVE_PATTERN_1 = 0;
	public static final int MOVE_PATTERN_2 = 1;
	public static final int MOVE_PATTERN_3 = 2;
	
	boolean dead = false;
	public int movetype;
	
	public int hp;
	public float speed;
	
	public long LastShoot = System.currentTimeMillis();
	
	public Enemy(Bitmap bitmap) {
		super(bitmap);
		
	}
	
	
	

	

}
