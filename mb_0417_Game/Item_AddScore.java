package com.example.mb_0417_Game;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Item_AddScore extends Item {
	
	public Item_AddScore(int x, int y){
		super(AppManager.getInstance().getBitmap(R.drawable.star12));
		this.InitSpriteDate(70, 46, 30, 10);
		
		m_x = x;
		m_y = y;
	
	}
	
	public void getItem(){
		AppManager.getInstance().m_gamestate.m_score += 100;
	}
	
	
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_BoundBox.set(m_x, m_y, m_x+30, m_y+30);
		
		m_x -= 4;
		
		
		if(m_x < 0){
			state = STATE_OUT;
		}
	}


}
