package com.example.mb_0417_Game;

import android.graphics.Bitmap;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;

public class Missile_Enemy extends Missile {

	Missile_Enemy(int x, int y){
		super(AppManager.getInstance().getBitmap(R.drawable.e_ms));
		this.InitSpriteDate(40, 86, 1, 1);
		this.SetPosition(x, y);
		//m_bitmap = Bitmap.createScaledBitmap(m_bitmap, Player.img_x/6, Player.img_y/8, false);
	}
	
	public void Update(long GameTime){
		super.Update(GameTime);
		m_x -= 15;
		if(m_x < 0){
			state = STATE_OUT;
		}
		
		m_BoundBox.set(m_x, m_y, m_x+86, m_y+40);
	}
	
	
}
