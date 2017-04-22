package Objet;



import org.newdawn.slick.geom.Vector2f;

import com.bombershinobi.Mot.Entity;


public class Player extends Entity {

	public Player(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public void setPosition (int x, int xx) {
		this.setPosition(new Vector2f (x, xx));
	}
	

}
