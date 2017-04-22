package State.concret;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bombershinobi.Main.MainGameState;

import Objet.ClientPlay;
import State.State;
import Tool.LoadingInfo;
import Tool.Screen;

public class Introduct extends State {

	
	
	private LoadingInfo info; private boolean draw;
	private boolean load;
	private int p1x, p1y;
	private int p2x, p2y;
	
	
	public Introduct (MainGameState j) {
		super (j);
		draw = false;
		load = false;
		
	}
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			 {
		enter = true;
		
		
		info = new LoadingInfo();
		manager.getRessource().clear();
		
			
		try {
			 manager.loadRessource(new FileInputStream("Ressource/Descriptor/lang.xml") , true, info);
} catch (FileNotFoundException e) {
			System.out.println("No file of lang find");
		} 	
			 
			 if (manager.nextState() == MainGameState.MENU)
				try {
					manager.loadRessource(new FileInputStream ("Ressource/Descriptor/menu.xml"), true, info );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println ("No file menu.xml found ... exiting.");
				}
			else if (manager.nextState() == MainGameState.MAINMENU)
				try {
					manager.loadRessource(new FileInputStream    ("Ressource/Descriptor/loadMenu.xml") , true, info);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println ("No file loadMenu.xml found ... exiting.");
				}
			else if (manager.nextState() == MainGameState.JEU) {
				

	try {
		manager.loadRessource (new FileInputStream ("Ressource/Descriptor/loadGameHUD.xml"), false, info);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println ("No file loadGame.xml found ... exiting.");
		System.exit (-10);
		}
				
			   	try {
					manager.loadRessource (new FileInputStream ("Map/" + manager.battle.getMap() + "/map.xml"), false, info);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println ("No file map.xml found ... exiting.");
					
					System.exit (-10);
					}
			}
			
			else {
				try {
					manager.loadRessource (new FileInputStream ("Ressource/Descriptor/loadSelection.xml") , true, info);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			System.out.println (info.getNext());
			
					
			
			
	}

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		
		arg2.setColor(Color.white);
		arg2.fillRect(0, 0, arg0.getWidth(), arg0.getWidth());
		arg2.setColor   (Color.red);
		
		
		
		
		
		
		if (manager.nextState() == -1 ) {
			ClientPlay p1;
			ClientPlay p2;
			ClientPlay p3;
			ClientPlay p4;
			
			p1 = manager.battle.getPlayerNumber (1);
			p2 = manager.battle.getPlayerNumber (2);
			p3 = manager.battle.getPlayerNumber (3);
			p4 = manager.battle.getPlayerNumber (4);
			Image p1Image = null;
			Image p2Image = null;
			Image p3Image = null;
			Image p4Image = null;
			
			if ((p1 == null) == false)
			 p1Image = manager.getRessource().getImage("INTRO_"+p1.getSelect());
			if ((p2 == null) == false)
			 p2Image = manager.getRessource().getImage("INTRO_"+p2.getSelect());
			if ((p3 == null) == false)
			 p3Image = manager.getRessource().getImage("INTRO_"+p3.getSelect());
			if ((p4 == null) == false)
			 p4Image = manager.getRessource().getImage("INTRO_"+p4.getSelect());
			
			if ((p1Image == null) == false )
			{
				arg2.drawImage (p1Image, p1x, p1y);
			}
			if ((p2Image == null) == false) {
				arg2.drawImage (p2Image, p2x, p2y);
			}
			
				
				
		}
		else {
		
		Image i = manager.getRessource().getImage("INTRO");
		Image ii = manager.getRessource().getImage("INTRO").getScaledCopy(arg0.getWidth(), arg0.getHeight());
		double  x =  ii.getWidth() / (double) i.getWidth();
		double y = ii.getHeight() /  (double) i.getHeight();
		
		
		for (int infoCharg= 1; infoCharg < info.getDone() + 1; infoCharg++)
			 arg2.fillRect(Screen.getX(arg0, 0.2f), Screen.getY(arg0, 0.88f) - ( ( Screen.getY(arg0, 0.9f) / info.getNext() ) * ( infoCharg - 1 ) )    , (float) ( ( arg0.getWidth() * x )  ) ,   (int) ( Screen.getY(arg0, 0.9f)  / info.getNext()   ) );

		arg2.drawImage  (manager.getRessource().getImage("INTRO").getScaledCopy(arg0.getWidth(), arg0.getHeight()), 0, 0);
		
		arg2.drawString(  (   info.getDone() / (double) info.getNext()   ) * 100 + "       % "  , 110, 200);
		
		
		arg2.setColor(Color.white);
		
		arg2.setColor(Color.red);
		
		}
		
		
		draw = true;
	}
	

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
		if (LoadingList.get().getRemainingResources()>0 && draw) {
			try {
				 LoadingList.get().getNext().load();
			} catch (IOException e) {
			}
			
			info.addDone();
		}
		
		
		if (LoadingList.get().getRemainingResources() == 0) 
			 quit(manager.nextState());
		
		draw = false;
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 7;
	}
	

	public void quit (int s) {
		
		enter = false;
		manager.enterState(s);
	}


}
