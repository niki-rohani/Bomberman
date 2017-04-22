package com.bombershinobi.Manager;

import java.util.HashMap;

import org.newdawn.slick.Sound;

import com.bombershinobi.Main.MainGameState;

public class MusicManager {
	
	
	
	
	
	private boolean isMusicPlay;
	private boolean isMusicLoop;
	private Sound music;
	private HashMap <String, Sound> sound;
	private static int _SOUND;
	
	
	
	
	public MusicManager () {
		isMusicPlay = false;
		isMusicLoop = false;
		music = null;
		sound = new HashMap <String, Sound>  ()  ;
		_SOUND = 0;
		
	}

	
	public void playMusic(String musicString, boolean loop) {
		        music = MainGameState.CONTROLLER.getRessource().getSound(musicString);
		        isMusicPlay = true;
		        isMusicLoop = true;
		        if (loop)
		        	music.loop();
		        else
		        	music.play();
	}
	
	public void playSound (String soundString) {
		sound.put (_SOUND+"", MainGameState.CONTROLLER.getRessource().getSound(soundString));
		sound.get(_SOUND+"").play();
		_SOUND++;
	}
	
	public void playSound (String soundString, String soundRef, boolean loop) {
		sound.put (soundRef+"ref", MainGameState.CONTROLLER.getRessource().getSound(soundString));
		if (loop)
			sound.get(soundRef+"ref").loop();
		if (loop == false) 
			sound.get (soundRef+"ref").play();
	}
	
	public void stopSound (String soundRef) {
		if (sound.get(soundRef+"ref") == null)
			return;
		sound.get(soundRef+"ref").stop();
	}
	
	public void stopMusic () {
		if (music == null)
			return;
		music.stop();
	}
	
	
	
	public boolean isMusicPlay () {
		return isMusicPlay;
	}
	
	
	public boolean isMusicLoop  () {
		return isMusicLoop;
	}
}
