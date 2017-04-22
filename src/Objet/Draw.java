package Objet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.bombershinobi.Main.MainGameState;

public abstract class Draw implements Comparable<Draw> {

	
	
	public double drawy;
	public double drawx;
	
	
	
	public abstract void render (Graphics g, GameContainer c, MainGameState ressource, int x, int y, int w, int h, Object o); 
	
	@Override
	public int compareTo(Draw arg0) {
		if (drawy < ((Draw) arg0).drawy )
					return -1;
			else
				return 1;
		
		
	}
	
	public String toString () {
		return "I am a renderer objet  " + drawx + " " + drawy;
	}
	
	
}
