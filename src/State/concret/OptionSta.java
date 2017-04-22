package State.concret;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


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

import com.bombershinobi.Main.MainGameState;

import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;

import rendu.*;

import Objet.Resolution;
import State.State;
import Tool.Option;
import Tool.Screen;

public class OptionSta extends State {

	private int w  ,  h;
	private TextField field;
	private TrueTypeFont font;
	
	
	private boolean firstanimationDone;
	private int step = 0;
	private int maxStep = 40;
	private int xd = 437 , yd = 284;
	private double xf = xd * 1.5 , yf = yd  * 1.5   ;

	private boolean lastAnim;
	
	ConfigurableEmitter emitter  ;
	
	
	
	
	SelectRendu resolution;
	
	
	
	
	
	ParticleSystem system;
	
	
	EditField editField;
	EditField editField2;
	
	public OptionSta (MainGameState j) {
		super (j);
		enter = false;
		 super.selectMax = 3;
	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		w = 410;
		 h = 200;
		 
		 
		
		try {
			font = new TrueTypeFont( Font.createFont(Font.TRUETYPE_FONT, new File ("Ressource/dekiru.ttf")).deriveFont(24.0f), true);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		editField =  new EditField(); editField.addCallback(new EditField.Callback() {
            public void callback(int key) { 
            	
                if(key == Event.KEY_RETURN) {
                    // cycle through 3 different colors/font styles
                	if (editField.getText().equals("") == false) {
        				select = 1;
        				editField.focusNextChild();
        			} 
                	
                
            }
            
        }
            });
		
				
				
				
				
			
	
		
		firstanimationDone = false;
				
			
		
		
		
		
	
				
	}
	
	
	public void enter (GameContainer arg0, StateBasedGame arg) {
		super.enter(arg0, arg);
	
		
		if (resolution == null)
			resolution = new SelectRendu (manager.resolution);
		
		 
		 select = 0;
		 
		
		 
		 system =  new ParticleSystem(manager.getRessource().getImage("FIRE_PARTICULE"),1500);
			try {
				File xmlFile = manager.getRessource().getFile("FILE_XML_FIRE");
				emitter = ParticleIO.loadEmitter(xmlFile);
				emitter.setPosition(Screen.getX(arg0, 0.8f), Screen.getY(arg0, 0.84f));
				system.addEmitter(emitter);
				
				
			
			} catch (Exception e) {
				System.out.println("Exception: " +e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
	 
			system.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
			
			
	}

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		arg2.setColor(Color.black);
		
		
		arg2.drawImage  (manager.getRessource().getImage("BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight()), 0, 0);
		arg2.drawImage  (Screen.getScaled(arg0,    0.4f, manager.getRessource().getImage("OPTION_MOON") ), Screen.getX(arg0, 0.6f), Screen.getY(arg0, 0.09f ))  ;
		arg2.drawImage (manager.getRessource().getImage("OPTION_BACK").getScaledCopy  (arg0.getWidth(), arg0.getHeight()) , 0 , 0     );
		arg2.drawImage (Screen.getScaled(arg0, 0.69f, manager.getRessource().getImage("OPTION_P") ), Screen.getX(arg0, 0.24f),   Screen.getY(arg0, 0.12f)) ;
		
		
				
		arg2.setColor(Color.black);
		
		
		arg2.setColor(Color.black);
		
		arg2.drawString("Entrez l'adresse ip : ", Screen.getX(arg0, 0.4f), Screen.getY(arg0, 0.3f) - 30);
		arg2.drawString("Entrez le port : ", Screen.getX(arg0, 0.4f), Screen.getY(arg0, 0.3f) + 70  );
		
	
		
		
		
		
		system.render();
	}

	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
	
		
		
		
		
		
		
		
		resolution.update(arg0);
		select(arg0);
		
		if (select == 2) {
			if (manager.isPressed("ACCEPT")) {
			
			quit(manager.GOTOOPTION);
			
			manager.getMusicManager().playSound("WIND_SOUND");
			}
			
			if (manager.isPressed("CANCEL")) {
				quit(manager.GOTOOPTION);
				
				manager.getMusicManager().playSound("WIND_SOUND");
				
			}
		
		}
		
		system.update(arg2);
		
			
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	public void quit (int s, GameContainer arg) {
		super.quit(s);
	}
	
	
	
	
		
		
		
		
	}
	
	
	
	

	


