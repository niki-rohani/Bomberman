package State;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bombershinobi.Main.MainGameState;



public abstract class State extends  BasicTWLGameState {

	
	
	public boolean enter;
	public MainGameState manager;
	public  int number;
	protected int select;
	protected int selectMax;
	protected int stateOfState;
	public static int QUIT = 0;
	public static int PAUSE = 1;
	
	public State(MainGameState main) {
		enter = false;
		manager = main ;
		select = 0;
		selectMax = 0;
		stateOfState = -1;
	}
	
	
	public void enter (GameContainer arg, StateBasedGame arg0) {
		try {
			super.enter(arg, arg0);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enter = true;
		number = 0;
		
		
		
		select = 0;
		manager.getParticleManager().clearParticule();
	}
	
	public void quit (int s) {
		enter = false;
		manager.setLastState( manager.getCurrentStateID()  ) ;
		manager.enterState(s);
		
		number = 0;
	}
	
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		
				
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg)
			throws SlickException {

		manager.getParticleManager().renderParticuleBack();
		
		
		
		
		
		if (enter)
			renderS (arg0, arg1, arg);
		
		manager.getParticleManager().renderParticuleFront();
		
	}
	
	public void update (GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		
		
		
	   if (number < 3)
		   arg0.getInput().consumeEvent();
		if (number > 3 && enter)
		
			updateS (arg0, arg1, arg2);
		
		number ++;
		
		
		
		manager.getParticleManager().nextParticule(arg2);
		
		manager.getEventManager().update (arg2);
		
	}

	public void renderS(GameContainer arg, StateBasedGame arg1, Graphics arg00) throws SlickException { }
	
	public void updateS (GameContainer arg, StateBasedGame arg1, int s) throws SlickException { }
	
	
	public void select (GameContainer arg) {
		if (arg.getInput().isKeyPressed(Input.KEY_DOWN))
			select = select + 1;
		if (arg.getInput().isKeyPressed(Input.KEY_UP))
			select = select - 1;
		
		if (select < 0) {
			select = 0;
		}
		
		if (select > selectMax)
			select = selectMax;
	}
}
