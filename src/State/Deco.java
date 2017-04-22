package State;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bombershinobi.Main.MainGameState;
import com.net.Packet.Packet2Message;


public class Deco extends State {

	MainGameState manager;
	
	public Deco (MainGameState j) {
		super (j);
	}
	
	final private static int ID = 3;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString(" YOU DECO ", 50, 0);
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	

}
