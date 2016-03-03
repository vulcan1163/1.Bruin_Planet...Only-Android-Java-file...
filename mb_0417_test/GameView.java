package  com.example.mb_0417_test;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.mb_0417_Game.Background;
import com.example.mb_0417_Game.GameState;

public class GameView extends SurfaceView implements Callback {
	
	public GameViewThread m_Thread;
	private IState m_state;
	private GraphicObject m_image;
	Background Back;
	public static int width;
	public static int height;
	public Vibrator m_vibrator;

	public GameView(Context context, AttributeSet attr) {
		super(context, attr);
		
		setFocusable(true);
		Back = new Background();
		
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		width = display.getWidth();
		height = display.getHeight();
		
		m_vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		
		SoundManager.getInstance().Init(context);
		SoundManager.getInstance().addSound(1, R.raw.missile);
		SoundManager.getInstance().addSound(2, R.raw.ex_2);
		
		
		
		AppManager.getInstance().setGameView(this);
		AppManager.getInstance().setResources(getResources());
		
		getHolder().addCallback(this);
		
		ChangedGameState(new GameState());
		
		m_Thread = new GameViewThread(getHolder(), this);
		
	}
	
	public void Vibratortest(int x){
		m_vibrator.vibrate(x);
	}
	
	public void DrawChractor(Canvas canvas){
		
		m_state.Render(canvas);
				
	}
	
	public void Update(){
		
		m_state.Update();
		postInvalidate();
	}
	
	public void ChangedGameState(IState state){
		
		if(m_state != null)
			m_state.Destroy();
			state.Init();
			m_state=state;
		
	}
	
	public void StopGame(){
		m_Thread.StopThread();
		
	}
	
	public void PauseGame(){
		m_Thread.PauseNResume(true);
	}
	
	public void ResumeGame(){
		m_Thread.PauseNResume(false);
	}
	

	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	
	}


	public void surfaceCreated(SurfaceHolder arg0) {
		m_Thread.setRunning(true);
		m_Thread.start();
	
	}


	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		
		m_Thread.setRunning(false);
		while(retry){
			try{
				
			m_Thread.join();
			retry = false;
			
		}catch(InterruptedException e){}
	}
	}
	
	public boolean onTouchEvent(MotionEvent event){
		
		return m_state.onTouchEvent(event);
		
	}



}
