package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.R;

import android.graphics.Bitmap;

public class Item_Health extends Item{

	public Item_Health(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.item2_12));
		this.InitSpriteDate(100, 100, 30, 19);
		m_x = x;
		m_y = y;
		
	}

	public void getItem(){
		AppManager.getInstance().m_player.AddLife();
	}
	
	
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_BoundBox.set(m_x, m_y, m_x+100, m_y+100);
		
		m_x -= 4;
		
		
		if(m_x < 0){
			state = STATE_OUT;
		}
	}


	
}
