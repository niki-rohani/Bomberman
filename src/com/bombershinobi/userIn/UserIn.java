package com.bombershinobi.userIn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.Input;

import Objet.Resolution;

public class UserIn {

	private int inputInterface;
	private HashMap <String, Integer> mapKeyboard;
	private HashMap <String, Integer> mapJoystick;
	
	
	
	
	public static int KEYBOARD = 0;
	public static int JOY = -1;
	
	private String[] map = {"UP", "DOWN", "RIGHT", "LEFT", "ENTER", "ACCEPT", "CANCEL", "SPACE" };
	
	
	
	public UserIn () {
		inputInterface = KEYBOARD;
		mapKeyboard = new HashMap <String, Integer> ();
		mapInit();
		
	}
	
	public boolean isDown(String c, Input key) {
		
		if (inputInterface == KEYBOARD) {
			return key.isKeyDown (mapKeyboard.get(c) );
		}
		
		return false;
	}
	
	public boolean isPressed (String c, Input key) {
		
		
		if (inputInterface == KEYBOARD) {
			return key.isKeyPressed (mapKeyboard.get(c) );
		}
		
		return false;
	}
	
	
	
	public void mapInit () {
		mapKeyboard.put("DOWN", Input.KEY_DOWN);
		mapKeyboard.put("UP", Input.KEY_UP);
		mapKeyboard.put("RIGTH", Input.KEY_RIGHT);
		mapKeyboard.put("LEFT", Input.KEY_LEFT);
		mapKeyboard.put("CANCEL", Input.KEY_C);
		mapKeyboard.put("ENTER", Input.KEY_ENTER);
		mapKeyboard.put("ACCEPT", Input.KEY_V);
		mapKeyboard.put("SPACE", Input.KEY_SPACE);
		
	}
	
	
	public int getInput()   {
		return inputInterface;
	}
	
	
	
	public boolean setKeyMap (String s, int touch) {
		boolean r = false;
		mapKeyboard.remove(s);
		mapKeyboard.put(s, touch);
		return r;
	}
	
	public boolean loadMap () {
		try {
			try {
				
			BufferedReader option = new BufferedReader (new FileReader (new File ("map.conf")));
			String s;
			inputInterface =    Integer.parseInt (option.readLine());
			while (  ( s = option.readLine()  ) != null ) {
				if (mapKeyboard.containsKey(s.split(":")[0]))
					mapKeyboard.remove(s.split(":")[0]);
				mapKeyboard.put(s.split(":")[0],   Integer.parseInt ( s.split(":")[1] )  );
			}
				
			option.close();
			
		} catch (IOException e  ) {
			return false;
		}
		}
			catch ( java.lang.NumberFormatException e) {
				return false;
			}
		return true;
	}
	
	
	public void save () {
		try {
			FileWriter w = new FileWriter (new File ("map.conf"));
		
		
		w.write(inputInterface  + "\r" );
		
		for (int i = 0; i < map.length ; i++ ) {
			w.write   (map[i]    + ":" +     mapKeyboard.get(map[i]) + "\r" );
		}
		
		
		w.flush();
		w.close();
		}
		catch (IOException e) { }
	
	}
	
	
}
