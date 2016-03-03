package com.example.mb_0417_Game;

import java.util.Random;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.R;

public class Enemy_1 extends Enemy {
	
	Random rnd = new Random();
	
	int type;
	public Enemy_1(int x, int y){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy_2_2));
		this.InitSpriteDate(300, 200, 10, 6);
		hp = 2;
		speed = 2.0f;
		
		m_x = x;
		m_y = y;
		
	}
	public void Update(long GameTime){
		super.Update(GameTime);
		Attack();
	
		
		m_BoundBox.set(m_x+50, m_y+50,  m_x+150, m_y+150);
		
	}


	
	public void Move(int test){
		type = test;
		
		switch(type){
		case 0:
			if(m_x >= GameView.width / 2){
				m_x -= speed;
			}else{
				m_x -= speed *2;
			}
			
		case 1:
			
			if(m_x >= GameView.width/2){
				m_x -= speed;
			}else{
				m_x -= speed;
				m_y -= speed;
			}
			
		case 2:
			if(m_x >= GameView.width/2){
				m_x -= speed;
			}else{
				m_x -= speed;
				m_y += speed;
			}
		}
		/*
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
		*/
		if(m_x < 0){
			state = STATE_OUT;
		}
		
	}
	
	public void Attack(){
		if(System.currentTimeMillis() - LastShoot >= 3000){
			LastShoot = System.currentTimeMillis();
			
			AppManager.getInstance().m_gamestate.m_ems.add(new Missile_Enemy(m_x, m_y+60));
			
		}
	}
	
	public void destroyEnemy(){
		hp--;
	}
	
	public int getLife(){
		return hp;
	}
	
	public void dead(boolean state){
		
		
		dead = state;
	}

	

}
