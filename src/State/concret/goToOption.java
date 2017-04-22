package State.concret;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.StateBasedGame;

import particuleObjet.particule;

import State.State;
import Tool.ImageScale;
import Tool.Screen;

import com.bombershinobi.Main.MainGameState;



public class goToOption extends State {

	
	
	private boolean firstanimationDone;
	private int x;
	private int y;
	private int animation = 2000;
	
	int tx = 0;
	int tx2 ;
	
	int fx = 0;

	private boolean lastAnim;

	private ParticleSystem system;

	private ConfigurableEmitter emitter;
	
	
	
	
	public goToOption (MainGameState j) {
		super (j);
		enter = false;
	 super.selectMax = 3;
	
	}
	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		firstanimationDone = false;
		x = 0;
		y = 0;
				
	}
	
	public void enter (GameContainer arg0, StateBasedGame arg) {
		super.enter(arg0, arg);
		select = 0;
		x = 0;
		
		tx = 0;
		tx2 = arg0.getWidth() ;
		 
		 
		 manager.getParticleManager().addParticuleFront(new particule (0, arg0.getHeight() / 2, manager.getRessource().getFile("FILE_XML_WIND"), manager.getRessource().getImage("FIRE_PARTICULE")));
		
		 File xmlFile;
				if (manager.getLastState() == manager.OPTION) {
				xmlFile = manager.getRessource().getFile("FILE_XML_WIND");
				try {
					emitter = ParticleIO.loadEmitter(xmlFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				emitter.setPosition(0, arg0.getHeight() / 2);
				
				}
				
				if (manager.getLastState() == manager.MENU || manager.getLastState() == MainGameState.CONNEXION)  {
				xmlFile = new File("Ressource/part/wind2.xml");
				try {
					emitter = ParticleIO.loadEmitter(xmlFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				emitter.setPosition(arg0.getWidth() / 2, arg0.getHeight() / 2);
				
				}
				
				 
				
				 xmlFile = new File("Ressource/part/particule.xml");
				
				
				try {
					emitter = ParticleIO.loadEmitter(xmlFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				system.addEmitter(emitter);
				
				
				
			
	}

	@Override
	public void renderS(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		int xx = 0;
		int hh = 0;
		
		
		
		
		
		int mx = 0;
		if (manager.getLastState() == manager.MENU) {
		 xx = arg0.getWidth() * 3 - ( ( ( arg0.getWidth() * 3 ) / animation )  * x )  ;
		 hh = 0 - y;
		 mx = 0 - ( ( ( arg0.getWidth() * 3 ) / animation )  * x ) ;
		 tx = tx - ( ( ( arg0.getWidth() * 3 ) / animation ) * 23 );
		 
		 
		 tx2 = tx2 - ( ( ( arg0.getWidth() * 3 ) / animation ) * 23  ) ;
		 
		
		 
		 
		 if (tx < - ( arg0.getWidth()) + arg0.getWidth() / 4  )
			 tx = arg0.getWidth() ;
		 if (tx2 < -arg0.getWidth() + arg0.getWidth() / 4 )
			 tx2 = arg0.getWidth() - 100 ;
		 
		 
		 if (xx < 0)   {
			 xx = 0;
			 firstanimationDone = true;
		 }
			 
//		 else
//			 fx =  fx - 20 ;
			
		
		}
		
		else if (manager.getLastState() == manager.OPTION) {
			 mx = arg0.getWidth() * 3 + ( ( ( arg0.getWidth() * 3 ) / animation )  * x )  ;
			 hh = 0 - y;
			 xx = 0 + ( ( ( arg0.getWidth() * 3 ) / animation )  * x ) ;
			 tx = tx + ( ( ( arg0.getWidth() * 3 ) / animation ) * 23 );
			 
			 
			 tx2 = tx2 + ( ( ( arg0.getWidth() * 3 ) / animation ) * 23  ) ;
			 
			
			 
			 
			 if (tx >  ( arg0.getWidth()) + arg0.getWidth() / 4  )
				 tx = - arg0.getWidth() ;
			 if (tx2 > arg0.getWidth() + arg0.getWidth() / 4 )
				 tx2 = - arg0.getWidth() - 100 ;
			 
			 
			 if (mx < 0) {
				 mx = 0;
				 firstanimationDone = true;
			 }
//			 else
//				 fx =  fx + 20 ;
			 
			 
		}
		
		arg2.drawImage  (manager.getRessource().getImage("BACK").getScaledCopy(arg0.getWidth() + 200, arg0.getHeight()),  fx , 0);
		
	
		
		arg2.drawImage(manager.getRessource().getImage("GOTOOPTION_BACK").getScaledCopy    (arg0.getWidth(), arg0.getHeight() ) , tx, 0 );
		arg2.drawImage(manager.getRessource().getImage("GOTOOPTION_BACK").getScaledCopy    (arg0.getWidth(), arg0.getHeight() ) , tx2, 0 );
		arg2.drawImage(manager.getRessource().getImage("GOTOOPTION_BACK").getScaledCopy    (arg0.getWidth(), arg0.getHeight() ) , 0, 0 );
		
		
		arg2.drawImage  (Screen.getScaled(arg0,    0.4f, manager.getRessource().getImage("OPTION_MOON") ), fx + Screen.getX(arg0, 0.6f), Screen.getY(arg0, 0.09f ))  ;
		arg2.drawImage (manager.getRessource().getImage("OPTION_BACK").getScaledCopy  (arg0.getWidth(), arg0.getHeight()) , xx , hh     );
		arg2.drawImage (Screen.getScaled(arg0, 0.69f, manager.getRessource().getImage("OPTION_P") ), xx + Screen.getX(arg0, 0.24f),   hh +   Screen.getY(arg0, 0.12f)) ;
		emitter.setPosition(   xx + Screen.getX(arg0, 0.8f),    hh + Screen.getY(arg0, 0.84f));
		 
		
		
		
		arg2.drawImage(ImageScale.getScaledImage(0.3, arg0, manager.getRessource().getImage("MENU_TORI")),      mx +  Screen.getX(arg0, 0.34f),  Screen.getY(arg0, 0.522f) );
		Image b = manager.getRessource().getImage("MENU_BACK").getScaledCopy(arg0.getWidth(), arg0.getHeight());
		
		arg2.drawImage(b,  mx , hh  );
		
		
		
		system.render();
		
	}

	@Override
	public void updateS(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
	
		
		
		
		x = x + 49 ;
		if (x > animation + 400 && firstanimationDone)
			if (manager.getLastState() == manager.MENU)
			quit (manager.OPTION);
			else if (manager.getLastState() == manager.OPTION)
			quit (manager.MENU);
			else
			quit (manager.MENU);
		
		
		
		system.update(arg2);
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 8;
	}
	
	
	
	public void quit (int s, GameContainer arg) {
		super.quit(s);
		
		
		
		
		
		
	}
	
	
	

	

}
