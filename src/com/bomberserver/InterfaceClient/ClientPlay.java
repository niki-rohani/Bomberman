package com.bomberserver.InterfaceClient;

import com.bomberserver.main.BomberServer;

public class ClientPlay {

	
	
	private Integer playerID;
	private boolean dead;
	private int playerCharac;
	private int bonusBomb;
	private int bonusPow;
	private boolean transformed;
	private int selection;
	private boolean select;
	private double x;
	private double y;
	private int direction;
	public static int DOWN = 1;
	public static int UP = 2;
	public static int RIGTH = 3;
	public static int LEFT = 4;
	public static int IDL = 0;
	public int energie;
	
	
	public static int DEIDARA = 1;
	
	public double speed;
	
	public int playerNumber;
	
	public boolean ready;
	
	
	private int mapBomb;
	
	
	
	public static int [] xPos = {  (int) ((100/15.0)*2.5), (int) ((100/15.0)*13.5), (int) ((100/15.0)*2.5), (int) ((100/15.0)*13.5) };
	
	public static int [] yPos = { (int) ((100/13.0)*2), (int) ((100/13.0) * 2), (int) ((100/13.0) * 11), (int) ((100/13.0) * 11)  };
	
	public ClientPlay(int player) {
		playerID = player;
		dead = false;
		bonusBomb = 8;
		bonusPow = 6;
		transformed = false;
		selection = player;
		select = false;
		x = 0;
		y = 0;
		direction = 0;
		speed = 1;
		playerCharac = 1;
		ready = false;
		mapBomb = 0;
		energie = 0;
	}
	
	public void changeDirection (int direc) {
		direction = direc;
	}
	
	public void move (boolean move) {
	
		if (direction == 1 && move)
			y = y + ( 0.5 * speed ) ;
		else if (direction == 3 && move)
			x = x + ( 0.5 * speed ) ;
		else if (direction == 4 && move)
			x = x - ( 0.5 * speed ) ;
		else if (direction == 2 && move)
			y = y - ( 0.5  * speed ) ;
		else
			direction = 0;
	    if (x > 99.5 || x < 0.5 || y > 99.5 || y < 0.5)
		{
			if (x > 99)
				x = 99.5;
			if (x < 0.5)
				x = 0.5;
			if (y > 99.5)
				y = 99.5;
			if (y < 0.5)
				y = 0.5;
			
			direction = 0;
			return;
		}
		
			}
	
	
	public void ready() {
		ready = true;
	}
	
	public boolean isReadyToFigth() {
		return ready;
	}
	
	
	
	
	
	public int getPw() {
		return bonusPow;
	}
	
	public double getX () {
		return   x ;
	}
	
	public double getY () {
		return   y  ;
	}
	
	public void addEnergie (int e) {
		energie = energie + e;
	}
	
	public boolean canTransform () {
		if (energie > 1000) {
			return true;
		}
		return false;     
	}
	
	
	
	public int getPersonnage() {
		return playerCharac;
	}
	
	public int getDirection () {
		return direction;
	}
	
	
	
	
	public void setCharac (int c) {
		playerCharac = c;
	}
	
	public void addBomb() {
		bonusBomb ++;
	}
	
	
	public void addSpeed() {
		speed = speed + 0.3;
	}
	public void addPw () {
		bonusPow   ++;
	}
	

	
	public int k() {
		return playerID;
	}
	
	public boolean isAlive() {
		return  !dead ;
	}
	
	
	

	public String serial() {
		int i = 1;
		if (dead)
			i = 0;
		int j = 0;
		if (select)
			j = 1;
		return playerID + ":" + i + ":" + playerCharac + ":" + bonusBomb + ":"
				+ bonusPow  + ":" + j + ":" + transformed + ":" + playerNumber ;
	}


	

	public int getSelection () {
		return selection ;
	}
	
	
	

	
	
	public void select (int select) {
		selection = select;
	}
	
		
	public boolean ready (boolean b) {
		x = xPos [playerNumber - 1];
		y = yPos [playerNumber - 1];
		
		if (select == b)
			return false;
		select = b;
		return true;
	}
	
	public boolean isReady() {
		return select;
	}
	
	

	
	public void explode () {
		dead = true;
	}
	
	public boolean isDead () {
		return dead;
	}
	
	
	public void setPosition (double [] player) {
		x = player[0];
		y = player[1];
	}
	
	public int getBomb () {
		return bonusBomb - mapBomb;
	}
	
	public void addBombPose () {
		mapBomb++;
	}
	
	public void deleteBomb() {
		mapBomb--;
	}
	
	
	public void restart (double [] p) {
		x = p[0];
		y = p[1];
		dead = false;
		ready = true;
	}
	
	
}
