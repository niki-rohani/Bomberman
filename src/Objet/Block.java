package Objet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

public class Block extends Draw {

	
	int block;
	
	
	public Block (int ressource, double xmen, double yromain) {
		block = ressource;
		drawx = xmen;
		drawy = yromain;
		
	}
	@Override
	public void render(Graphics g, GameContainer c, MainGameState ressource,
			int x, int y, int w, int h, Object o) {
		// TODO Auto-generated method stub
		Image render = Screen.getScaledWidth ( (int)    (  ressource.battle.getBoard().getCaseWidth (w)  )  + 1  , ressource.getRessource().getImage ("BLOCK" + block ));
		g.drawImage (render , x +  (float)          ( ( drawx / 100.0 ) * w  )  , y + (float)  (  ( drawy  / 100.0 ) * h  ) - render.getHeight() );
	}

}
