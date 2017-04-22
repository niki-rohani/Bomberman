package State.concret;

import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bombershinobi.Main.MainGameState;
import com.esotericsoftware.kryonet.Client;

import rendu.TextFieldOption;

import State.State;
import Tool.Animation;
import Tool.Option;
import Tool.Screen;

public class Connexion extends State {

	@SuppressWarnings("unused")
	private int w  ,  h;
	@SuppressWarnings("unused")
	private TextField field;
	
	@SuppressWarnings({ "unused", "deprecation" })
	private TrueTypeFont font;
	
	@SuppressWarnings("unused")
	private TextField field2;
	
	public boolean connecting = false;
	
	public boolean erreur = false;
	
	 private boolean enterze;
	private float alph = 1.0f;

	private boolean sound;
	
	private Animation porteTori;
	
	
	public Connexion (MainGameState j) {
		super (j);
		
	}
	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		w = container.getWidth() / 4;
		h = 20;
		font = new TrueTypeFont(new Font("Arial", 1, 28), true);
	}

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		Image i   = manager.getRessource().getImage("MENU_BACK").getScaledCopy(arg0.getWidth() , arg0.getHeight()  )  ;
		i.setAlpha(alph);
		arg2.drawImage  (i, 0 - porteTori.getStep(), 0 - porteTori.getStep());
		porteTori.render(arg2);
		
		if (connecting)
			arg2.drawString("Chargement", Screen.getX(arg0, 0.4f) , Screen.getY(arg0, 0.2f) );
		
	}

	
	public void enter (GameContainer arg0, StateBasedGame arg1) {
		super.enter(arg0, arg1);
	
	
		
		 erreur = false;
		 alph = 1.0f;
		
		
		new Thread() { public void run() { connecting = true; erreur =  !manager.getNetworkManager().connect(); connecting = false; }}.start();
		sound = false;
		
		porteTori = new Animation (manager.getRessource().getImage("MENU_TORI").getScaledCopy(arg0.getWidth(), arg0.getHeight()).getScaledCopy(0.3f), 100, arg0.getWidth() * 0.34, arg0.getHeight() * 0.522 , 10 );
		
	}
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
				if (manager.getNetworkManager().isConnected()) {
					alph = alph - ( 1.f / ( porteTori.getMaxStepToEnd() ));
					porteTori.update();
					if (!sound) { 
						manager.getRessource().getSound("SOUND_JOUER_MENU").play();
						sound = true;
						System.out.println("Sound being play");
						manager.getMusicManager().stopMusic();
					}
			
				
					
					
					
					if (porteTori.isAnimationDone()) { 
						 manager.setNextS(MainGameState.MAINMENU); quit(MainGameState.INTRO); } }
				else if (erreur == true) {
					manager.getNetworkManager().getClient().stop();
					
					manager.enterState(manager.OPTION);  erreur = false;
					
				} }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 20;
	}

}
