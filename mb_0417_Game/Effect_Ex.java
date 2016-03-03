package com.example.mb_0417_Game;

import android.graphics.Bitmap;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SpriteAnimation;

public class Effect_Ex extends SpriteAnimation {

	public Effect_Ex(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.ex3));
		this.InitSpriteDate(60, 64, 10, 16);
		
	//	m_bitmap = Bitmap.createScaledBitmap(m_bitmap, 404, 404, false);
		
		//super.Scale(204, 204);
		m_x = x;
		m_y = y;
		
		mbRelay = false;
	}

	
}
