package com.example.mb_0417_test;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundManager {
	
	public SoundPool m_SoundPool;
	private HashMap m_SoundPoolMap;
	private AudioManager m_AudioManager;
	private Context m_Activity;
	int  m_index;
	
	public void Init(Context _context){
		m_SoundPool = new SoundPool (4, AudioManager.STREAM_MUSIC, 0);
		m_SoundPoolMap = new HashMap();
		m_AudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
		m_Activity = _context;
	
	}
	
	public void addSound(int _index, int _soundID){
		
		int id = m_SoundPool.load(m_Activity, _soundID, 1);
		m_SoundPoolMap.put(_index, id);
	}
	
	public void play(int _index){
	// m_index = _index;
		//m_SoundPool.setOnLoadCompleteListener (new OnLoadCompleteListener() {

		  //  @Override

		 //   public void onLoadComplete(SoundPool m_SoundPool, int soundId, int status) {
		float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	
		m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, 0, 1f);
		  //  }

		//});
		    
 }
	
	public void playLooped(int _index){
		
		float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, -1, 1f);
	}
	
	private static SoundManager s_instance;
	
	public static SoundManager getInstance(){
		if(s_instance == null){
			s_instance = new SoundManager();
		}
		return s_instance;
	}

}
