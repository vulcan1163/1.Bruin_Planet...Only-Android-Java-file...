package com.example.mb_0417_Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.SpriteAnimation;

public class Player extends SpriteAnimation{

	Rect m_BoundBox = new Rect();
	Rect m_BoundBox_1 = new Rect();
	Rect m_BoundBox_2 = new Rect();
	
	int p_x, p_y;
	int m_Life = 10;
	
	public static int img_x;
	public static int img_y;
	
	private float y;
	private int yy;
	
	public Player(Bitmap bitmap) {
		super(bitmap);
		this.InitSpriteDate(300, 264, 7, 6);
		
		img_x = GameView.width/3;
		img_y = GameView.height/3;
		
		AppManager.getInstance().setPlayer(this);
		SetPosition(p_x, p_y);
	}
	
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_y += (int) (AppManager.getInstance().m_activity.ACCY)* 8f;
		m_x += (int)(AppManager.getInstance().m_activity.ACCX)* 8f;
		
		p_x = m_x;
		p_y = m_y;
	
		if(m_x < 0 ){
			m_x = 0;
		}
		
		if(m_x > GameView.width/2){
			m_x = GameView.width/2;
		}
		if(m_y < 0 ){
			
			m_y = 0;
			
		}else if(m_y > GameView.height -100)
		{
		
		m_y = GameView.height - 100;
		
		}
		
		m_BoundBox.set(m_x+60, m_y+45, m_x+235, m_y+75);
		m_BoundBox_1.set(m_x+130, m_y+75, m_x+160, m_y+100);
		m_BoundBox_2.set(m_x+130, m_y+20, m_x+160, m_y+60);
	}
	
	
	public int getLife(){
		return m_Life;
	}
	
	public void AddLife(){
		
		if(m_Life >= 10){
			m_Life = 10;
		}
		m_Life++;
	}
	
	public void destroyPlayer(){
		m_Life--;
	}
	
}
