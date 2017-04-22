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
import org.newdawn.slick.Image;
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

import com.bombershinobi.Main.MainGameState;
import com.net.Packet;
import com.net.Packet.Packet200JoinBack;
import com.net.Packet.Packet28BattleSelect;
import com.net.Packet.Packet2Message;

import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.textarea.HTMLTextAreaModel;

import rendu.TextField;
import shout.ShoutBox;

import Objet.ClientPlay;
import State.State;
import Tool.Option;
import Tool.Screen;

public class MenuJeu extends State {

	
	
	
	
    
	private org.newdawn.slick.Font font;
	
	
	
	
	
	
	
	
			
	public MenuJeu (MainGameState j) {
		super (j);
	}
	
	final private static int ID = 31;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		font = null;
		
	    
       
	}

	

	

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		
		g.setColor(Color.black);
		
			
		g.drawImage( manager.getRessource().getImage("MENU_SELECT_BACK").getScaledCopy(         arg0.getWidth (),   arg0.getHeight ()       ) , 0, 0);
		
			Image deidara = manager.getRessource().getImage("MENU_SELECT_DEIDARA_MIN_1");
		Image naruto = manager.getRessource().getImage("MENU_SELECT_NARUTO_MIN");
		Image orochimaru = manager.getRessource().getImage("MENU_SELECT_OROCHIMARU_MIN_1");
		Image sasuke = manager.getRessource().getImage("MENU_SELECT_SASUKE_MIN_1");
		
		
		
		int [] p = {0,0,0,0} ; 
		
		Image p1 = manager.getRessource().getImage ("MENU_SELECT_1P");
		Image p2 = manager.getRessource().getImage ("MENU_SELECT_2P");
		Image p3 = manager.getRessource().getImage ("MENU_SELECT_3P");
		Image p4 = manager.getRessource().getImage ("MENU_SELECT_3P");
		
		Image p1Select = null;
		Image p2Select = null;
		Image p3Select = null;
		Image p4Select = null;
		
		
		
		
		
		String [] image = {"DEIDARA", "NARUTO", "OROCHIMARU", "SASUKE"};
		
		boolean p1ready = false;
		boolean p2ready = false;
		boolean p3ready = false;
		boolean p4ready = false;
		
		if (manager.battle == null)
		return;
		if  (manager.battle.players == null)
		return;
		 for (  int i = 0; i < manager.battle.players.length; i++ ) {
			 ClientPlay c = manager.battle.players[i];
			 if (c == null ) {
				
			 }
			 else  {
				 
				 	if (c.getSelect() == 1) {
				 	 
				 		
					 deidara = manager.getRessource().getImage("MENU_SELECT_DEIDARA_MIN_" + c.playerNumber);
					 System.out.println(c.playerNumber);
					 p [i] = (int)   ( Screen.getX(arg0, 0.1f ) + deidara.getWidth() / 2.0 - Screen.getX(arg0, 0.02f)  )     ;
				 	}
				 	else if (c.getSelect()  == 2 ) {
					 naruto = manager.getRessource().getImage("MENU_SELECT_NARUTO_MIN_" + c.playerNumber);
					 p [i] = (int)   ( Screen.getX(arg0, 0.24f ) + deidara.getWidth() / 2.0 - Screen.getX(arg0, 0.02f)  );
				 	}
				 	else if (c.getSelect() == 3) {
					  orochimaru = manager.getRessource().getImage("MENU_SELECT_OROCHIMARU_MIN_" + c.playerNumber);
					 p[i] = (int)   ( Screen.getX(arg0, 0.38f ) + deidara.getWidth() / 2.0 - Screen.getX(arg0, 0.02f)  );
				 	}
				 	else if (c.getSelect() == 4) {
					  sasuke = manager.getRessource().getImage("MENU_SELECT_SASUKE_MIN_" + c.playerNumber);
					p[i] = (int)   ( Screen.getX(arg0, 0.51f ) + deidara.getWidth() / 2.0 - Screen.getX(arg0, 0.02f)  );
				 	}
				 	
				 	if (c.playerNumber == 1) {
				 		p1Select = manager.getRessource().getImage ("MENU_SELECT_"+ image [c.getSelect() - 1] + "_" + c.playerNumber );
				 		if (c.isSelect())
				 			p1ready = true;
				 	}
				 	if (c.playerNumber == 2) {
				 		p2Select = manager.getRessource().getImage ("MENU_SELECT_"+ image [c.getSelect() - 1] + "_" + c.playerNumber );
						if (c.isSelect())
				 			p2ready = true;
				
				 	}
				 	if (c.playerNumber == 3) {
				 		p3Select = manager.getRessource().getImage ("MENU_SELECT_"+ image [c.getSelect() - 1] + "_" + c.playerNumber );
						if (c.isSelect())
				 			p3ready = true;
				
				 	}
				 	if (c.playerNumber == 4) {
				 		p4Select = manager.getRessource().getImage ("MENU_SELECT_"+ image [c.getSelect() - 1] + "_" + c.playerNumber );
				 		if (c.isSelect())
				 			p4ready = true;
				 	}
				 	
				 	
				 	
				 	
				 	
					 
			 }
			if (p[0] > 0)
		g.drawImage (Screen.getScaled (arg0, 0.7f, p1Select), Screen.getX(arg0, 0.04f), Screen.getY(arg0, 0.1f));
			if (p[1] > 0)
		g.drawImage (Screen.getScaled (arg0, 0.7f, p2Select), Screen.getX(arg0, 0.24f), Screen.getY(arg0, 0.1f));
			if (p[2] > 0)
		g.drawImage (Screen.getScaled (arg0, 0.7f, p3Select), Screen.getX(arg0, 0.44f), Screen.getY(arg0, 0.1f));
			if (p[3] > 0)
		g.drawImage (Screen.getScaled (arg0, 0.7f, p4Select), Screen.getX(arg0, 0.64f), Screen.getY(arg0, 0.1f));
		 
		
		g.drawImage (  manager.getRessource().getImage ("MENU_SELECT_BAND") , 0, Screen.getY(arg0, 0.6f) );
		
		
			 g.drawImage (Screen.getScaled(arg0, 0.17f, deidara), Screen.getX(arg0, 0.1f), Screen.getY(arg0, 0.7f));
			 g.drawImage (Screen.getScaled(arg0, 0.17f, naruto), Screen.getX(arg0, 0.24f), Screen.getY(arg0, 0.7f));
			 g.drawImage (Screen.getScaled(arg0, 0.17f, orochimaru), Screen.getX(arg0, 0.38f), Screen.getY(arg0, 0.7f));
			 g.drawImage (Screen.getScaled(arg0, 0.17f, sasuke), Screen.getX(arg0, 0.52f), Screen.getY(arg0, 0.7f));
			  
			 if (p[0] > 0) {
			 g.drawImage (Screen.getScaled (arg0, 0.04f, p1), p[0], Screen.getY(arg0, 0.63f));
			 }
			 if (p[1] > 0)
				 g.drawImage (Screen.getScaled (arg0, 0.04f, p2), p[1], Screen.getY(arg0, 0.63f));
			 if (p[2] > 0)
				 g.drawImage (Screen.getScaled (arg0, 0.04f, p3), p[2], Screen.getY(arg0, 0.63f));
			 if (p[3] > 0)
				 g.drawImage (Screen.getScaled (arg0,    0.04f, p4), p[3], Screen.getY(arg0, 0.63f));
			 
			 
			 
		 }
		 
		 
		 
		  Image menu = manager.getRessource().getImage ("MENU_ACCEPT");
		 if (p1ready)
			 g.drawImage (Screen.getScaled (arg0, 0.2f, menu), Screen.getX(arg0, 0.14f), Screen.getY(arg0, 0.38f));
		 
		 if (p2ready)
			 g.drawImage (Screen.getScaled (arg0, 0.2f, menu), Screen.getX(arg0, 0.34f), Screen.getY(arg0, 0.38f));
		
		 if (p3ready)
			 g.drawImage (Screen.getScaled (arg0, 0.2f, menu), Screen.getX(arg0, 0.54f), Screen.getY(arg0, 0.38f));
		
		 if (p4ready)
			 g.drawImage (Screen.getScaled (arg0, 0.2f, menu), Screen.getX(arg0, 0.74f), Screen.getY(arg0, 0.38f));
		
		 
			manager.getUIObjet("menu_chat").render(g, manager, arg0);
			
			   manager.guirender(arg0, g);
		
			   
			   if (stateOfState == QUIT) {
				   Color c = g.getColor();
				   g.setColor (Color.black);
				   Image imageQu = manager.getRessource().getImage ("MENU_SELECT_BACK").getSubImage(50, 50, 1, 1);
				 
				   imageQu = imageQu.getScaledCopy (arg0.getWidth(), arg0.getHeight() );
				   imageQu.setAlpha (0.4f);
				   g.drawImage (imageQu, 0f, 0f)    ;
				   
				   
				   g.drawString ("APPUIYEZ SUR ACCEPT POUR RETOURNER AU MENU, APPUIYEZ SUR CANCEL POUR ANNULER "  , Screen.getX(arg0, 0.28f), Screen.getY(arg0, 0.04f));
				   g.setColor (c);
				   
				   
			   }
			
	}

	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	
		
		if (manager.getNetworkManager().isConnected() == false) {
			
			manager.setNextS(1);
			quit(manager.INTRO);
			
		}
		
		if (manager.battle.isRun() ) {
			manager.setNextS(MainGameState.JEU);
			quit(manager.INTRO);
		}
		
		if (font == null)
			 
	        
	       
	        try {
				font = new TrueTypeFont (Font.createFont(Font.TRUETYPE_FONT, manager.getRessource().getFile("FONT")), true);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		 
		 if (manager.isPressed ("RIGTH") && stateOfState == -1 && focus) {
			 Packet.Packet28BattleSelect p = new Packet.Packet28BattleSelect ();
			 p.player = 1;
			 p.client = manager.getNetworkManager().getId();
			 p.battle = manager.battle.getId();
			 manager.getNetworkManager().getClient().sendTCP (p);
		 }
		 else if (manager.isPressed ("LEFT") && stateOfState ==     -1 && focus) {
			 Packet.Packet28BattleSelect p = new Packet.Packet28BattleSelect ();
			 p.player = -1;
			 p.client = manager.getNetworkManager().getId();
			 p.battle = manager.battle.getId();
			 manager.getNetworkManager().getClient().sendTCP (p);
		 }
		 else if (manager.isPressed ("ACCEPT") && focus) {
			 if (manager.getPlay().isSelect() == false &&   ( stateOfState == QUIT ) == false ) {
			 Packet.Packet201BattleSendS p = new Packet.Packet201BattleSendS ()   ;
			 p.battle = manager.battle.getId();
			 p.select = manager.getNetworkManager().getId();
			 p.selection = true;
			 manager.getNetworkManager().getClient().sendTCP (p);
			 }
			 else if (stateOfState == QUIT) {
				 manager.battle = null;
				 manager.setNextS (1);
				 quit (MainGameState.INTRO);
				 
			 }
			 
		 }
		 else if (manager.isPressed ("CANCEL") && focus) {
			 if (manager.getPlay().isSelect() && stateOfState == -1) {
				 Packet.Packet201BattleSendS p = new Packet.Packet201BattleSendS ()   ;
				 p.battle = manager.battle.getId();
				 p.select = manager.getNetworkManager().getId();
				p.selection = false;
				 manager.getNetworkManager().getClient().sendTCP (p); 
				 }
			 else if (stateOfState == -1) {
				 stateOfState = QUIT;
  
			 }
			 else {
				 stateOfState = -1;
			 }
				 
		 }
		 
			 
			
		 
		 
		 manager.getUIObjet ("menu_chat").update (arg0, manager);
			 manager.getUIObjet ("menu_chat" ).focus();
			 manager.getUIObjet ("menu_chat" ).setVisible();
				 
		 manager.Update (arg0, arg2);
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
public void enter (GameContainer arg0, StateBasedGame arg) {  super.enter (arg0, arg);  enter = true;

getRootPane().removeAllChildren();
Packet28BattleSelect p = new Packet28BattleSelect ();
p.battle = manager.battle.getId();
p.client = manager.getNetworkManager().getId();
p.player = -1;

	manager.getNetworkManager().getClient().sendTCP (p);




manager.getMusicManager().playMusic("SOUND_SELECT", true);

manager.addUIObjet("menu_chat", new rendu.uiObject.chat (0, 0, 1.0f, arg0, manager, ShoutBox.BATTLE, getRootPane() ));



}




public void leaver (GameContainer arg0, StateBasedGame argg) {
	try {
		arg0.setFullscreen(false);
	} catch (SlickException e) {
		e.printStackTrace();
	}
}



	
	public void quit (int s) {
		stateOfState = -1;
		super.quit(s);
		manager.getMusicManager().stopMusic();
		
		
		
	}


}
