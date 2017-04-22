package Objet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

import java.util.HashMap;

public class ClientPlay extends Draw {

	private int playerID;
	private boolean dead;
	private int playerCharac;
	private int bonusBomb;
	private int bonusPow;
	private double posX, posY;
	public int direction;
	public Animation animation;
	public double speed;
	public int playerNumber;
	public boolean select;
	public float[] xoff = { (float) 0.5, (float) 0.5, (float) 0.5, (float) 0.7} ;
	public float[] yoff = { (float) 0.96, (float) 0.95, (float) 0.85 , (float) 0.89 };

	public ClientPlay(int player) {
		playerID = player;
		dead = false;
		bonusBomb = 0;
		bonusPow = 0;
		posX = 0;
		posY = 0;
		direction = 4;
		speed = 1;
		select = false;
		
	}

	public void setCharac(int c) {
		playerCharac = c;
	}
	
	
	public void setDirection (int direc) {
		direction = direc;
	}
	
	
	public double getX (int echelle) {
		return  ( posX / 100.0 )  *  echelle;
	}
	
	public double getY(int echelle) {
		return  ( posY  / 100.0 ) * echelle ;
	}
	
	
	
	
	public void setAnimation (Animation a) {
		animation = a;
	}
	
	public Animation nextAnimation (long delta) {
		animation.setSpeed( (float) speed);
		animation.update(delta);
		return animation  ;
	}
	
	

	public void addBomb() {
		bonusBomb++;
	}

	public void addPw() {
		bonusPow++;
	}

	public int getID() {
		return playerID;
	}
	
	public boolean isSelect() {
		return select;
	}
	
	public void select() {
		select = true;
	}
	
	public void selectChange() {
		select = false;
	}

	
	
	public void setPosition (double x, double y) { posX = x; posY = y ; drawx = x; drawy = y;  } 
	
	public boolean isAlive() {
		return !dead;
	}
	
	public int getSelect () {
		return playerCharac;
	}

	public String serial() {
		int i = 1;
		if (dead)
			i = 0;
		int j = 0;
		if (select)
			j = 1;
		return playerID + ":" + i + ":" + playerCharac + ":" + bonusBomb + ":"
				+ bonusPow  + ":" + j + ":" + playerNumber ;
	}

	
	
	public void load(String[] t) {

		playerID = Integer.parseInt(t[0]);
		if (t[1].equals (0))
			dead = true;
		playerCharac = Integer.parseInt(t[2]);
		bonusBomb = Integer.parseInt(t[3]);
		int bonusP = Integer.parseInt(t[4]);
		bonusPow = bonusP;
		if (Integer.parseInt(t[5]) == 0)
			select = false;
		else
			select = true;
		playerNumber = Integer.parseInt (t[7]);

	}
	
	@Override
	public void render(Graphics g, GameContainer c, MainGameState ressource,
			int x, int y, int w, int h, Object o) {
		
		if (dead == false) {
		  g.drawImage (    Screen.getScaled (h   / ressource.battle.getBoard().getH(), (float) 1,  ((HashMap <String, Animation>) o) .get(playerNumber + "").getCurrentFrame()) ,  x + (float) getX(w) - xoff[playerCharac - 1] * Screen.getScaled (h   / ressource.battle.getBoard().getH(), (float) 0.9,  ((HashMap <String, Animation>) o) .get(playerNumber + "").getCurrentFrame()).getWidth()
				  , y + (float) getY (h) - yoff  [playerCharac - 1] * Screen.getScaled (h   / ressource.battle.getBoard().getH(), (float) 0.9,  ((HashMap <String, Animation>) o) .get(playerNumber + "").getCurrentFrame()).getHeight() )  ;
		}
		
					 
	}
	
	public void dead () {
		dead = true;
	}
	
	
	public String toString () {
		  return  "I am a player " + drawx + " " + drawy;
	}
	
}
