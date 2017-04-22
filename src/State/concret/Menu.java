package State.concret;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bombershinobi.Main.MainGameState;

import State.State;
import Tool.ImageScale;
import Tool.Screen;

public class Menu extends State {

	
	private boolean sound = false;
	private int select;
	
	public Menu (MainGameState j) {
		super (j);
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		select = 0;
				}

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		Image i = manager.getRessource().getImage("BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight());
		Image b = manager.getRessource().getImage("MENU_BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight());
		i.setAlpha(1.0f);
		arg2.drawImage  (i, 0, 0);
		arg2.drawString(  String.valueOf ( select ) , 0, 0);
		
		arg2.drawImage(Screen.getScaled (arg0,  0.39f , manager.getRessource().getImage("MENU_TORI")),  Screen.getX(arg0, 0.34f),  Screen.getY(arg0, 0.47f) );
		
		
		
		
		Image jouer = Screen.getScaled (arg0, 0.10f, manager.getRessource().getImage("MENU_BUTTON_PLAY")) ;
		Image option = Screen.getScaled (arg0, 0.10f, manager.getRessource().getImage("MENU_BUTTON_OPTION") )    ;
		Image end =  Screen.getScaled(arg0, 0.10f, manager.getRessource().getImage("MENU_BUTTON_END")  ) ;
		
		int xj, xo, xf;
		
		xj = Screen.getX(arg0, 0.387f);
		xo = xj;
		xf = xo;
		
		
		if (select == 0) {
			jouer =  Screen.getScaled(arg0, 0.095f, manager.getRessource().getImage("MENU_BUTTON_PLAYPRESS")); xj = xj + 10; }
		else if (select == 1) {
			option = Screen.getScaled(arg0, 0.095f, manager.getRessource().getImage("MENU_BUTTON_OPTIONPRESS"))  ; xo = xo + 10; }
		else {
			end =   Screen.getScaled(arg0, 0.095f, manager.getRessource().getImage("MENU_BUTTON_END")) ; 
			xf = xf + 10; }
		
			
		arg2.drawImage(jouer, xj, Screen.getY(arg0, 0.608f));
		arg2.drawImage(option, xo, Screen.getY(arg0, 0.67f));
		arg2.drawImage(end, xf, Screen.getY(arg0, 0.729f));
		arg2.drawImage  (Screen.getScaled(arg0,    0.4f, manager.getRessource().getImage("OPTION_MOON") ), Screen.getX(arg0, 0.6f), Screen.getY(arg0, 0.09f ))  ;
		
		
		
		arg2.drawImage (b, 0, 0);
		
		
		
		
	}

	public void enter(GameContainer arg0, StateBasedGame arg1) {
		super.enter  (arg0, arg1);
		
			manager.getMusicManager().playMusic ( "SOUND_MENU", true);
			}
	
	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
		if (manager.isPressed("DOWN")) {
			manager.getMusicManager().playSound("SOUND_SWITCH_MENU");
			select = select + 1;
			if (select > 2)
				select = 0;
		}
		
		if (manager.isPressed("UP")) {
			manager.getMusicManager().playSound("SOUND_SWITCH_MENU");
			select = select - 1;
			if (select < 0) {
				select = 2;
			}
		}
		
		if (manager.isPressed("ACCEPT") && enter == true) {
			
			if (select == 0)
				quit(MainGameState.CONNEXION);
			else if (select == 1) { 
				quit(manager.GOTOOPTION);
				manager.getMusicManager().playSound ("WIND_SOUND");
				
			}
			else
				manager.quit();
		}
		
		enter = true;

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

	

	
	public void quit (int s) {
		super.quit (s);
	}

}
