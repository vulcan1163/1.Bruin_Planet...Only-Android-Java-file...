package com.example.mb_0417_Game;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.mb_0417_test.AppManager;
import com.example.mb_0417_test.GameView;
import com.example.mb_0417_test.IState;
import com.example.mb_0417_test.R;
import com.example.mb_0417_test.SoundManager;

public class GameState implements IState {
	int soundCount;
	public float t_x, t_y;
	public int m_score = 0;
	public int combo = 0;
	Random randEnem = new Random();
	Background m_back;
	Context context;
	Player m_player;
	public Anerge m_an;
	boolean start=false;
	boolean comboStart = false;
	Random rnd = new Random();
	Enemy_2 enem_2;
	Enemy_1 enem;
	
	public int px , py;//player À§Ä¡

	ArrayList<Item_Health> m_item_health = new ArrayList<Item_Health>();
	ArrayList<Item_AddScore> m_item_score = new ArrayList<Item_AddScore>();
	ArrayList<Effect_Ex_Enemy> EX_enemy = new ArrayList<Effect_Ex_Enemy>();
	ArrayList<Effect_Ex> EX = new ArrayList<Effect_Ex>();
	ArrayList<Enemy_1> m_enemy = new ArrayList<Enemy_1>();
	ArrayList<Enemy_2> m_enemy_2 = new ArrayList<Enemy_2>();
	public ArrayList<Missile_Player> m_pms = new ArrayList<Missile_Player>();
	ArrayList<Missile_Enemy> m_ems = new ArrayList<Missile_Enemy>();

	int V_width, V_height;
	int rndEnem_2;
	int counter = 0;
	long thisTime, lastTime;
	long LastRegenEnemy;
	long LastDead;
	long LastCombo;
	
	boolean dangerSound = false;

	public GameState() {
		AppManager.getInstance().m_gamestate = this;
		LastRegenEnemy = System.currentTimeMillis();
		V_width = GameView.width;
		V_height = GameView.height;
	
	}

	public void Init() {
		m_back = new Background();
		
		m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player_1_1));
		m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_1));
		lastTime = System.currentTimeMillis();

	}

	public void Destroy() {

	}

	public void Render(Canvas canvas) {
		Paint p = new Paint();
		m_back.Draw(canvas);
		m_an.Draw(canvas);
		
		
		
		for (Enemy_1 enem : m_enemy) {
			enem.Draw(canvas);
		}

		for (Enemy_2 enem : m_enemy_2) {
			enem.Draw(canvas);
		}
		
		for (Missile_Player pms : m_pms) {
			pms.Draw(canvas);
		}

		for (Missile_Enemy ems : m_ems) {
			ems.Draw(canvas);
		}

		for (Effect_Ex ex : EX) {
			ex.Draw(canvas);
		}

		for (Effect_Ex_Enemy exe : EX_enemy) {
			exe.Draw(canvas);
		}

		for (Item_AddScore item : m_item_score) {
			item.Draw(canvas);
		}
		for (Item_Health item : m_item_health) {
			item.Draw(canvas);
		}
	

		

		p.setTextSize(30);
		p.setColor(Color.GREEN);
		canvas.drawText("È¹µæÁ¡¼ö  : " + String.valueOf(m_score) , 20, 110, p);
		
		p.setTextSize(30);
		p.setColor(Color.RED);
		canvas.drawText("ANERGE : ", 20, 60, p);
		
		
		
		p.setTextSize(30);
		p.setColor(Color.RED);
		canvas.drawText("COMBO ", 20, 160, p);
		
		p.setColor(Color.RED);
		
		
		
		Rect comboR = new Rect();

		comboR.set(20, 200, combo+20 ,230);
		p.setColor(Color.GREEN);
		canvas.drawRect(comboR, p);
		m_player.Draw(canvas);

	}

	public void Update() {

		long GameTime = System.currentTimeMillis();
		px = m_player.GetX();
		py = m_player.Gety() + 60;
		m_back.Update(GameTime);
		m_player.Update(GameTime);
		m_an.Update(GameTime);
		
		if(combo > 110){ 
			combo = 110;
		}
		
		switch(AppManager.getInstance().m_player.getLife()){
		case 10 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_1));
		break;
		case 9 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_2));
		break;
		case 8 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_3));
		break;
		case 7 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_4));
		break;
		case 6 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_5));
		break;
		case 5 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_6));
		break;
		case 4 :
			
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_7));
		
		
		break;
		case 3 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_8));
			AppManager.getInstance().m_activity.danger();
		break;
		case 2 :
			m_an = new Anerge(AppManager.getInstance().getBitmap(R.drawable.an_9));
			AppManager.getInstance().m_activity.danger();
	
		break;
		
		}
		

		for (int i = m_enemy.size() - 1; i >= 0; i--) {

			
			Enemy_1 enem = m_enemy.get(i);
			enem.Update(GameTime);
			
			
			if (enem.state == Enemy.STATE_OUT) {
				m_enemy.remove(i);
			}
			
			if(enem.dead == true){
				combo++;
			}
		
		}
		
		for (int i = m_enemy_2.size() - 1; i >= 0; i--) {

			Enemy_2 enem = m_enemy_2.get(i);
			enem.Update(GameTime);

			if (enem.state == Enemy.STATE_OUT) {
				m_enemy_2.remove(i);
			}
		}

		for (int i = m_pms.size() - 1; i >= 0; i--) {
			Missile pms = m_pms.get(i);

			pms.Update(GameTime);
			m_pms.get(i).move();

			if (pms.state == Missile.STATE_OUT) {
				m_pms.remove(i);
			}
		}

		for (int i = m_ems.size() - 1; i >= 0; i--) {
			Missile ems = m_ems.get(i);
			ems.Update(GameTime);

			if (ems.state == Missile.STATE_OUT) {
				m_ems.remove(i);
			}
		}

		for (int i = m_item_score.size() - 1; i >= 0; i--) {
			Item_AddScore ems = m_item_score.get(i);
			ems.Update(GameTime);

			if (ems.state == Item.STATE_OUT) {
				m_item_score.remove(i);
			}
		}

		for (int i = m_item_health.size() - 1; i >= 0; i--) {
			Item_Health ems = m_item_health.get(i);
			ems.Update(GameTime);

			if (ems.state == Item.STATE_OUT) {
				m_item_health.remove(i);
			}
		}

		for (int i = EX.size() - 1; i >= 0; i--) {
			Effect_Ex ex = EX.get(i);
			ex.Update(GameTime);
			if (ex.getAnimationEnd())
				EX.remove(i);
		}

		for (int i = EX_enemy.size() - 1; i >= 0; i--) {
			Effect_Ex_Enemy exe = EX_enemy.get(i);
			exe.Update(GameTime);
			if (exe.getAnimationEnd())
				EX_enemy.remove(i);
		}

	

		MakeEnemy();
		
		if(m_score > 300)
		MakeEnemy_2();
		
		
		CheckCollision();
		

		if(start){
			
			if(System.currentTimeMillis() - LastDead >= 6000){
				combo =0;
				start = false;
			}
		}
		
		if(comboStart){
			
			AppManager.getInstance().m_player.m_Life = 100;
			m_score += 100;
			if(System.currentTimeMillis()- LastCombo >= 5000){
				//m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player_1_1));
				m_score = 400;
				AppManager.getInstance().m_player.m_Life = 10;
				combo = 0;
				comboStart = false;
			}
			
		}
		
	
		
	}

	public boolean onKeyDown(int keycode, KeyEvent event) {
		int x = m_player.GetX();
		int y = m_player.Gety();
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {

		
		
		t_x = event.getX();
		t_y = event.getY();
		/*
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			if (System.currentTimeMillis() - LastRegenEnemy >= 500) {
				LastRegenEnemy = System.currentTimeMillis();
				
				m_pms.add(new Missile_Player(x, y));
				
				//SoundManager.getInstance().play(1);
				SoundManager.getInstance().m_SoundPool.play(1, 0.4f, 0.4f, 1, 0, 1);
			}
			

		}
		*/
		/*
		 * if (event.getAction() == MotionEvent.ACTION_MOVE) {
		 * 
		 * if(System.currentTimeMillis() - LastRegenEnemy >= 1200){
		 * m_pms.add(new Missile_Player( x, y));
		 * SoundManager.getInstance().play(1); }
		 * 
		 * t_x = event.getX(); t_y = event.getY();
		 * 
		 * }
		 */
		
		return true;
	}

	public void MakeEnemy() {

		/*
		 * if(System.currentTimeMillis() - LastRegenEnemy >= 3000){
		 * LastRegenEnemy = System.currentTimeMillis();
		 * 
		 * Enemy enem = new Enemy_1();
		 * 
		 * enem.SetPosition(GameView.width,
		 * randEnem.nextInt(GameView.height-100)); enem.movetype =
		 * randEnem.nextInt(3); m_enemy.add(enem);
		 * 
		 * 
		 * }
		 */
		
	
		for(int i = m_enemy.size()-1; i >=0; i--){
			m_enemy.get(i).Move( randEnem.nextInt(3));
		}
		
		rndEnem_2 = randEnem.nextInt(GameView.height - 100);
		if (m_enemy.size() > 4)
			return;
		m_enemy.add(new Enemy_1(V_width, rndEnem_2));
		
		

	} // end Make Enemy
	
	public void MakeEnemy_2(){
		

		
		for(int i = m_enemy_2.size()-1; i >=0; i--){
			m_enemy_2.get(i).move();
		}
		
		rndEnem_2 = randEnem.nextInt(GameView.height - 100);
		if (m_enemy_2.size() > 4)
			return;
		
		m_enemy_2.add(new Enemy_2(V_width, rndEnem_2));
		
	
	
	}
	
	
	
	

	public void CheckCollision() {

		for (int i = m_pms.size() - 1; i >= 0; i--) {
			for (int j = m_enemy.size() - 1; j >= 0; j--) {
				
				
				if (CollisionManager.CheckBoxToBox(m_pms.get(i).m_BoundBox, m_enemy.get(j).m_BoundBox)) {

					EX.add(new Effect_Ex(m_pms.get(i).GetX(), m_pms.get(i).Gety()));

					m_pms.remove(i);
					m_enemy.get(j).destroyEnemy();
					
					SoundManager.getInstance().m_SoundPool.play(2, 10f, 10f, 1, 0, 1);
					
					if (m_enemy.get(j).getLife() <= 0) {

						EX_enemy.add(new Effect_Ex_Enemy(m_enemy.get(j).GetX() + 20, m_enemy.get(j).Gety() + 20));
						
						CreateItem(m_enemy.get(j).GetX() + 20, m_enemy.get(j).Gety() + 20);
						
						m_enemy.get(j).dead(true);
						m_enemy.remove(j);
						m_score += 30;
						CheckCombo();
						combo += 10;
					if(combo >= 100){
						LastCombo = System.currentTimeMillis();
						comboStart = true;

						//m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player_1_2));
					}
						
					}
					
					return;
				}
				
				
			}
		}
		
		for (int i = m_pms.size() - 1; i >= 0; i--) {
			for (int j = m_enemy_2.size() - 1; j >= 0; j--) {
				
				
				if (CollisionManager.CheckBoxToBox(m_pms.get(i).m_BoundBox,m_enemy_2.get(j).m_BoundBox)) {

					
					
					EX.add(new Effect_Ex(m_pms.get(i).GetX(), m_pms.get(i).Gety()));
					EX_enemy.add(new Effect_Ex_Enemy(m_enemy_2.get(j).GetX() + 20, m_enemy_2.get(j).Gety() + 20));
					CreateItem(m_enemy_2.get(j).GetX() + 20, m_enemy_2.get(j).Gety() + 20);
					m_pms.remove(i);
					m_enemy_2.remove(j);
					SoundManager.getInstance().m_SoundPool.play(2, 10f, 10f, 1, 0, 1);
					m_score += 30;
					combo += 10;
					
					if(combo >= 100){
						LastCombo = System.currentTimeMillis();
						comboStart = true;

					//	m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player_1_2));
					}
					
					return;
					
					
				}
			}
		}

		for (int i = m_enemy.size() - 1; i >= 0; i--) {
			if (CollisionManager.CheckThePlayer(m_player.m_BoundBox,m_player.m_BoundBox_1,m_player.m_BoundBox_2, m_enemy.get(i).m_BoundBox)) {

				EX_enemy.add(new Effect_Ex_Enemy(m_enemy.get(i).GetX() + 20,
						m_enemy.get(i).Gety() + 20));
				AppManager.getInstance().m_gameview.m_vibrator.vibrate(800);
				m_enemy.remove(i);
				m_player.destroyPlayer();
				SoundManager.getInstance().m_SoundPool.play(2, 10f, 10f, 1, 0, 1);
				if (m_player.getLife() <= 0)
					AppManager.getInstance().m_activity.EndGame();
				
					return;
			}
		}
		
		for (int i = m_enemy_2.size() - 1; i >= 0; i--) {
			if (CollisionManager.CheckThePlayer(m_player.m_BoundBox,m_player.m_BoundBox_1,m_player.m_BoundBox_2,  m_enemy_2.get(i).m_BoundBox)) {

				EX_enemy.add(new Effect_Ex_Enemy(m_enemy_2.get(i).GetX() + 20,
						m_enemy_2.get(i).Gety() + 20));
				AppManager.getInstance().m_gameview.m_vibrator.vibrate(800);
				m_enemy_2.remove(i);
				m_player.destroyPlayer();
				SoundManager.getInstance().m_SoundPool.play(2, 10f, 10f, 1, 0, 1);
				if (m_player.getLife() <= 0)
					AppManager.getInstance().m_activity.EndGame();

			return;
			}
		}

		for (int i = m_item_score.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckThePlayer(m_player.m_BoundBox,m_player.m_BoundBox_1,m_player.m_BoundBox_2, m_item_score.get(i).m_BoundBox)) {

				m_item_score.get(i).getItem();

				m_item_score.remove(i);

				
				return;
			}
		}

		for (int i = m_item_health.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckThePlayer(m_player.m_BoundBox,m_player.m_BoundBox_1,m_player.m_BoundBox_2, m_item_health.get(i).m_BoundBox)) {

				m_item_health.get(i).getItem();

				m_item_health.remove(i);
				
				return;
			}
		}

		for (int i = m_ems.size() - 1; i >= 0; i--) {

			if (CollisionManager.CheckThePlayer(m_player.m_BoundBox,m_player.m_BoundBox_1,m_player.m_BoundBox_2,	m_ems.get(i).m_BoundBox)) {

				EX.add(new Effect_Ex(m_ems.get(i).GetX(), m_ems.get(i).Gety()));

				AppManager.getInstance().m_gameview.m_vibrator.vibrate(800);
				m_ems.remove(i);
				m_player.destroyPlayer();
				SoundManager.getInstance().m_SoundPool.play(2, 10f, 10f, 1, 0, 1);
				if (m_player.getLife() <= 0) {

					AppManager.getInstance().m_activity.EndGame();
				}
				
				return;

			}

		}
	} // end check Collision

	public void CreateItem(int x, int y) {
		int rand = rnd.nextInt(100);

		if (rand <= 50) {
			m_item_score.add(new Item_AddScore(x, y));

		} else
			m_item_health.add(new Item_Health(x, y));
	}
	
	public void CheckCombo(){
		LastDead = System.currentTimeMillis();
		start = true;
		
	}

}
