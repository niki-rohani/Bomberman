package com.bomberserver.main;

import java.util.Date;

public class Bomb {

	
	
	
	private int x, y;
	
	private double time;
	private boolean explode;
	
	
	private double type;
	private double port;
	
	private double lifeTime;
	
	
	private int id;
	
	
	
	private int clientID;

	public Bomb (int posx, int posy, double character, double r,  int _a, int client ) {
		x = posx;
		y = posy; 
		type = character;
		explode = false;
		time = new Date().getTime();
		port = r;
		id = _a;
		lifeTime = 3000;
		clientID = client;
	}
	
	public void explode () {
		explode = true;
	}
	
	public double getPort () {
		return port;
	}
	
	public void update() {
		
		if (new Date().getTime() - time > lifeTime ) {
			explode();
		}
		
	}
	
	public boolean isExplode() {
		return explode;
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}

	public int Poser() {
		// TODO Auto-generated method stub
		return clientID;
	}

	
	
	
}
