package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Missile_Player extends Missile {
	

	float t_x, t_y;
	public int speed =8;
	public int speed3 = 20;

	public Missile_Player(int x, int y){
		
	
		
		super(AppManager.getInstance().getBitmap(R.drawable.missile_main));
		

		m_x = x;
		m_y = y;
		
		this.InitSpriteDate(40, 86, 7, 6);
		this.SetPosition(m_x+40, m_y);
		
		
		t_x = AppManager.getInstance().m_gamestate.t_x;
		t_y = AppManager.getInstance().m_gamestate.t_y;
		//this.Scale(Player.img_x/6, Player.img_y/8);
		
	}
	
	public void Update(long GameTime){
		super.Update(GameTime);
		/*
		if(m_x < 200)
		m_x += 2;
		else
		m_x += 8;
		*/

	//	m_bitmap = Bitmap.createScaledBitmap(m_bitmap, Player.img_x/6, Player.img_y/8, false);
		
		
		
		if(m_x > GameView.width){
			state = STATE_OUT;
		}
		
		m_BoundBox.left = m_x;
		m_BoundBox.top = m_y;
		m_BoundBox.right = m_x+86;
		m_BoundBox.bottom = m_y+40;
		
	}
	
	public void move(){

		
		/*
		if(m_y < t_y ){
			m_y += speed+5;
			
			if(m_x < t_x + m_x){
				m_x +=speed;	
				if(m_x >= 200){			
				m_x += speed3;
			
				}
			
			}
		
		}
		if(m_y >  t_y ){				
			m_y -= speed+5;
			
			if(m_x < t_x + m_x){
				m_x +=speed;	
				if(m_x >= 200){			
				m_x += speed3;
			
				}
			
			}
			
		}
		*/
		
		m_x += 20;
	
	}
	
	
	
	

}
