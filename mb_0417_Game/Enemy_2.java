package com.example.mb_0417_Game;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Enemy_2 extends Enemy{


	Random rnd2 = new Random();
	int rad2;
	
	Bitmap stone[] = new Bitmap[3];
	
	public Enemy_2(int x, int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.stone_1_3));
		this.InitSpriteDate(130, 80, 1, 1);
		speed = 2.0f;
		hp = 1;
		
		stone[0] = AppManager.getInstance().getBitmap(R.drawable.stone_1_4);
		stone[1] = AppManager.getInstance().getBitmap(R.drawable.stone_1_3);
		stone[2] = AppManager.getInstance().getBitmap(R.drawable.stone_1_5);

		m_x = x;
		m_y = y;

		rad2 = rnd2.nextInt(2);
	}
	
	public void Update(long GameTime){
		super.Update(GameTime);
		move();
		m_BoundBox.set(m_x, m_y,  m_x+80, m_y+130);
	}
	
	public void Draw(Canvas canvas){
		switch(rad2){
		case 0 :
			m_bitmap = stone[0];
			break;
		case 1 :
			m_bitmap = stone[1];
			break;
		case 2 :
			m_bitmap = stone[2];
			break;
			
		}
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
		
	}
	
	public void move(){
		if(movetype ==MOVE_PATTERN_1 ){
			
			if(m_x >= GameView.width / 2){
				m_x -= speed;
			}else{
				m_x -= speed *2;
			}
		}
		
		
		else if(movetype == MOVE_PATTERN_2){
			
			if(m_x >= GameView.width/2){
				m_x -= speed;
			}else{
				m_x -= speed;
				m_y -= speed;
			}
		}
		else if(movetype == MOVE_PATTERN_3){
			
			if(m_x >= GameView.width/2){
				m_x -= speed;
			}else{
				m_x -= speed;
				m_y += speed;
			}
		}
		
		if(m_x < 0){
			state = STATE_OUT;
		}
	}

}
