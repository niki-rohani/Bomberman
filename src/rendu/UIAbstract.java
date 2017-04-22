package rendu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.bombershinobi.Main.MainGameState;

public abstract class UIAbstract {
	
	protected int positionX; protected int positionY;
	protected float size;
	private boolean focus;
	
	
	public UIAbstract (int x, int y, float mul) {
		positionX = x;
		positionY = y;
		size = mul;
		focus = false;
		
	}
	
	public abstract void render (Graphics g, MainGameState s, GameContainer arg);
	public abstract void update (GameContainer arg, MainGameState s);
	
	public void focus () {
		focus = true;
	}
	
	public void unfocus() {
		focus = false;
	}
	
	public void setVisible() {
		
	}
	
	public void setInVisible() {
		
	}
	
	
	
	
	public boolean hasFocus() {
		if (focus)
			return true;
		else
			return false;
	}
	
	public Object getInfo () {  return null; }
	
}
