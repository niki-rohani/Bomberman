package State.concret;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import State.State;

import com.bombershinobi.Main.MainGameState;


public class MainJeu extends State {

	
	private boolean sound = false;
	private int select;
	
	public MainJeu (MainGameState j) {
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
		Image i = manager.getRessource().getImage("MENU_BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight());
		i.setAlpha(1.0f);
		arg2.drawImage  (i, 0, 0);
		arg2.drawString(  String.valueOf ( select ) , 0, 0);
		arg2.drawImage(manager.getRessource().getImage("MENU_TORI").getScaledCopy(437,284), 285, 209);
		
		Image jouer = manager.getRessource().getImage("MENU_BUTTON_PLAY").getScaledCopy(300, 75);
		Image option = manager.getRessource().getImage("MENU_BUTTON_OPTION").getScaledCopy(280, 70);
		Image end = manager.getRessource().getImage("MENU_BUTTON_END").getScaledCopy(300, 75);
		
		int xj, xo, xf;
		
		xj = 360;
		xo = 360;
		xf = 350;
		
		
		if (select == 0) {
			jouer = manager.getRessource().getImage("MENU_BUTTON_PLAYPRESS").getScaledCopy(280, 70); xj = xj + 10; }
		else if (select == 1) {
			option = manager.getRessource().getImage("MENU_BUTTON_OPTIONPRESS").getScaledCopy(270, 60  ); xo = xo + 10; }
		else {
			end = manager.getRessource().getImage("MENU_BUTTON_END").getScaledCopy  (280, 70) ; 
			xf = 360; }
		
			
		arg2.drawImage(jouer, xj, 310);
		arg2.drawImage(option, xo, 370);
		arg2.drawImage(end, xf, 420);
		
		
	}

	public void enter(GameContainer arg0, StateBasedGame arg1) {
		super.enter  (arg0, arg1);
		
			manager.getMusicManager().playMusic ("SOUND_MENU", true);
			
			 }
	
	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
		if (arg0.getInput().isKeyPressed(Input.KEY_DOWN)) {
			manager.getMusicManager().playSound("SOUND_SWITCH_MENU");
			select = select + 1;
			if (select > 2)
				select = 0;
		}
		
		if (arg0.getInput().isKeyPressed(Input.KEY_UP)) {
			manager.getMusicManager().playSound("SOUND_SWITCH_MENU");
			select = select - 1;
			if (select < 0) {
				select = 2;
			}
		}
		
		if (arg0.getInput().isKeyPressed(Input.KEY_ENTER) && enter == true) {
			
			if (select == 0)
				quit(MainGameState.CONNEXION);
			else if (select == 1) {
				quit(0);
				manager.getMusicManager().playSound("SOUND_ENTER_MENU");
				
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
		enter = false;
		manager.enterState(s);
	}

}
