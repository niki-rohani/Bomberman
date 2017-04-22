package com.bombershinobi.Manager;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Sound;

import Objet.Bomb;
import Objet.ClientPlay;
import Objet.Draw;

import com.bombershinobi.Main.MainGameState;
import com.net.Packet;
import com.net.Packet.Packet206PoseBomb;

public class EventManager {

	ArrayList <Draw> animation;
	ArrayList <String> sound;
	
	
	
	public EventManager () {
		animation = new ArrayList <Draw>  ();
		sound = new ArrayList <String>   ();
	}
	
	
	
	public void event01BombPose (Packet.Packet206PoseBomb p) {
		MainGameState.CONTROLLER.battle.addBomb(new Bomb (p.x, p.y, p.playerCharacter, p.bomb, ((Packet206PoseBomb) p).tileX, ((Packet206PoseBomb) p).tileY)   ) ;
		
	}
	
	
	public void event02BattleSelect (int cl, int s) {
		for (ClientPlay personnage : MainGameState.CONTROLLER.battle.players ) 
			
			if ( personnage == null )  {  }
			else
			if (  personnage.getID() == cl )
				personnage.setCharac(s);
		  sound.add ("MENU_SELECT_SELECT");  
	}
	
	
	
	public void event03Disconnect() {
		if (MainGameState.CONTROLLER.getNetworkManager().getId() > 0)
			MainGameState.CONTROLLER.getNetworkManager().deconected();
	}
	
	
	public void update (int up) {
		ArrayList <String> sounds    =  (ArrayList<String>) sound.clone();
		for (String s : sounds)
			MainGameState.CONTROLLER.getMusicManager().playSound(s);
		sound.clear();
			
	}
	
	
	public ArrayList <Draw> getDraw () {
		return animation;
	}
	
	
}
