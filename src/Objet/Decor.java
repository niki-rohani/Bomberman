package Objet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

public class Decor extends Draw {

	
	
	private double  drawx;
	private double  drawy;
	private int dec;
	
	public Decor (double posX, double posY, int draw) {
		drawx = posX;
		drawy = posY;
		dec = draw;
	}
	@Override
	public void render(Graphics g, GameContainer c, MainGameState ressource,
			int x, int y, int w, int h, Object o) {
		// TODO Auto-generated method stub
		
		
		
		
		
		Image rend =  ressource.getRessource().getImage ("EXPLOSE" + dec).getScaledCopy ((int) ( ressource.battle.getBoard().getCaseWidth(w) ) , (int) (  ressource.battle.getBoard().getCaseHeight (h)) )   ; 
		if (dec > 0)
		g.drawImage (rend, x +        (int)    ( (drawx / 100.0) * w )       + Screen.centerX                 ( (  w /   (float) ressource.battle.getBoard().getL() ) , rend)  ,     (int)   ( (( drawy / 100.0)    * h )   -    ( rend.getHeight() / 1.5  )  )  );
	}
	
	
	public String toString() {
		return "I am a box in " + drawx + " " + drawy;
	}
	
	

}
