package State.concret;


import java.awt.Font;
import java.awt.FontFormatException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import particuleObjet.particule;

import com.bombershinobi.Main.MainGameState;
import com.net.Packet;
import com.net.Packet.Packet25BattleCreateAccept;
import com.net.Packet.Packet2Message;

import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.textarea.HTMLTextAreaModel;

import rendu.TextField;
import shout.ShoutBox;

import State.State;
import Tool.Option;
import Tool.Screen;

public class MainMenu extends State {

	
	
	
	
	private org.newdawn.slick.Font font;
	

	
	
	private int number;
	
	private int state = 1;
	
	
	
	public MainMenu (MainGameState j) {
		super (j);
	}
	
	final private static int ID = 1;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
		font = null;
		
		
	    
	    
	    number =   1 ;
       
	}

	

	

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		
		g.setColor(Color.black);
		
			
		
		g.drawImage(manager.getRessource().getImage("MENU_IN_BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight() ), 0, 0);
		
		manager.getUIObjet ("menu_chat" ).render (g, manager, arg0);
		 
		g.setFont(font);
		
		
		g.drawString("BIENVENUE SUR BOMBERSHINOBI", Screen.getX   (arg0, 0.5f), 10);
		g.drawImage(manager.getRessource().getImage("MENU_IN_BANDEAU"), 0, 0);
		g.drawImage(  Screen.getScaled(arg0, 0.76f, manager.getRessource().getImage("MENU_IN_PARCH"))  , Screen.getX(arg0,  0.3f), Screen.getY(arg0, 0.11f) );
		
		
		manager.getUIObjet("menu_sharingan").render(g, manager, arg0);
		
		if ( state == 1)  
			manager.getUIObjet("menu_jeu").render(g, manager, arg0);
		
		
		
		  manager.guirender(arg0, g);
			
		  
		
	}

	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
		
		
		if (manager.getNetworkManager().isConnected() == false) {
			
			manager.setNextS(manager.MENU);
			quit(manager.INTRO);
			
		}
		 
		if (state == 4) {
			manager.getNetworkManager().getClient().close();
		}
		 if ( state == 1) {
			manager.getUIObjet("menu_jeu").update(arg0, manager);
			manager.getUIObjet("menu_jeu").setVisible();
		 }
		 else
			 manager.getUIObjet("menu_jeu").setInVisible();
		
		if (font == null)
			 
	        
	       
	        try {
				font = new TrueTypeFont (Font.createFont(Font.TRUETYPE_FONT, manager.getRessource().getFile("FONT")), true);
				if (font == null)
					System.exit(0);
				
	        } catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		manager.getUIObjet("menu_sharingan").update(arg0, manager);
		if (manager.isPressed("ACCEPT")) {
			state = (int) manager.getUIObjet("menu_sharingan").getInfo();
			if (state == 1)  {
				manager.getUIObjet("menu_jeu").focus();
				manager.getUIObjet("menu_sharingan").unfocus();
			}
		}
		
		if (manager.isPressed("CANCEL")) {
			manager.getUIObjet("menu_sharingan").focus();
			manager.getUIObjet("menu_jeu").unfocus();
			Packet.Packet24Joinn p = new Packet.Packet24Joinn();
			p.b = 0;
			p.client = manager.getNetworkManager().getId();
			
			manager.getNetworkManager().getClient().sendTCP(p);
		}
		
		manager.Update(arg0, arg2);
		if (number % 30 == 0)
		 manager.getNetworkManager().getConected();
		
		
		 
	manager.getUIObjet ("menu_chat").update (arg0, manager);
	 manager.getUIObjet ("menu_chat" ).focus();
	 manager.getUIObjet ("menu_chat" ).setVisible();
		 
		 if (manager.isBattleRuning()) {
			 manager.setNextS (MainGameState.LOBBY); 
			 quit (MainGameState.INTRO);
		 }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
public void enter (GameContainer arg0, StateBasedGame arg) {  super.enter (arg0, arg);  enter = true;
super.enter (arg0, arg);

manager.getMusicManager().playMusic("SOUND_MENU_IN", true);

manager.addUIObjet("menu_sharingan", new rendu.uiObject.Menu(Screen.getX(arg0, -0.12f), Screen.getY(arg0, 0.3f), 0.4f));
manager.addUIObjet("menu_jeu", new rendu.uiObject.BCrender(Screen.getX(arg0 , 0.36f), Screen.getY(arg0, 0.24f), 100, 100, getRootPane()  )  );

manager.addUIObjet("menu_chat", new rendu.uiObject.chat (0, 0, 1.0f, arg0, manager, ShoutBox.MENU, getRootPane() ));





manager.getParticleManager().addParticuleFront (new particule (0, arg0.getHeight() / 2, manager.getRessource().getFile ("FILE_XML_WINDKONO"), manager.getRessource().getImage ("konoha_PARTICULE") ) );

}




public void leaver (GameContainer arg0, StateBasedGame argg) {
	try {
		arg0.setFullscreen(false);
	} catch (SlickException e) {
		e.printStackTrace();
	}
}


	
	public void quit (int s) {
		super.quit(s);
		manager.getMusicManager().stopMusic();
		
		
		
	}


}
