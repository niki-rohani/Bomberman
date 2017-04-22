package Objet;

import java.util.Date;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.bombershinobi.Main.MainGameState;

import Tool.Screen;

public class Bomb extends Draw {

	
	
	
	
	private double x, y;
	private int xx, yy;
	
	
	private double time;
	private boolean explode;
	
	
	private double type;
	private double port;
	
	private double lifeTime;
	
	public static int TILEFIRST = 10;
	public static int TILEEND = 20;
	
	
	public static int DEIDARA = 10;
	
	public Bomb (double posx, double posy, double character, double r, int tileX, int tileY) {
		x = posx;
		y = posy;
		type = character;
		explode = false;
		time = new Date().getTime();
		port = r;
		xx = tileX;
		yy = tileY;
		
		drawy = y;
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
	
	 public double getX(int ech) {
		return    (x / 100.0) * ech  ;
	}
	
	 public double getY(int ech) {
		return         (y/100.0)  * ech     ;
	}
	 
	 
	 public int getXX() {
		 return xx;
	 }
	 
	 
	 public int getYY() {
		 return yy;
	 }
	 
	 public void render (Graphics g, GameContainer c, MainGameState ressource, int x, int y, int w, int h, Object o) {
		 Image offx = Screen.getScaled(h, 1/ (float) ressource.battle.getBoard().getH() , ressource.getRessource().getImage ("BOMB") )   ;
		 g.drawImage (offx,  x + (float) getX(w) +  Screen.centerX(    w   / (float) ressource.battle.getBoard().getL() , offx) ,   y +   (float) getY(h));
			
	 }
	
	
	
}
