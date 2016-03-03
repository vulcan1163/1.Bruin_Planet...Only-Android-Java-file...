package  com.example.mb_0417_test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mb_0417_Game.GameState;
import com.example.mb_0417_Game.Player;

public class AppManager {
	
	public GameView m_gameview;
	public MenuSurfaceView m_MS;
	private Resources m_resources;
	public GameState m_gamestate;
	public MyActivity m_activity;
	public Player m_player;
	
	public Bitmap getBitmap(int r){
		return BitmapFactory.decodeResource(m_resources, r);
	}
	public void setGameView(GameView gameview){
		m_gameview = gameview;
		
	}
	
	public void setMenuSurfaceView(MenuSurfaceView MS){
		m_MS = MS;
	}
	public void setPlayer(Player player){
		m_player = player;
	}
	public void setActivity(MyActivity activity){
		m_activity = activity;
	}
	public void setResources(Resources resources){
		m_resources = resources;
	}
	public GameView getGameView(){
		return m_gameview;
	}
	public Resources getResources(){
		return m_resources;
	}
	
	private static AppManager m_instance;
	
	public static AppManager getInstance(){
		if(m_instance == null){
			m_instance = new AppManager();
		}
		return m_instance;
	}

}
