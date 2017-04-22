package com.bomberserver.main;

public class Bonus {

	
	
	public static int POWER = 1;
	public static int SPEED = 2;
	public static int CHACRA = 3;
	public static int BOMB = 4;
	private int bonn;
	private int bonusX;
	private int bonusY;
	
	public Bonus (int id, int x, int y) {
		bonn = id;
		bonusX = x;
		bonusY = y;
	}
	
	
	public int getX() {
		return bonusX;
	}
	
	
	public int getY() {
		return bonusY;
	}
	
	public int bonus () {
		return bonn;
	}
}
