package State.concret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.bombershinobi.Main.MainGameState;
import com.net.Packet;

import Objet.Bomb;
import Objet.ClientPlay;
import Objet.Draw;
import State.State;
import Tool.Screen;

public class Game extends State {

	
	
	
	
	private boolean b;
	private int f = 0;
	
	public Animation spr;
	
	private int playerNumber;
	
	private int direction;
	
	private boolean enter;
	
	private boolean stop;
	
	
	public HashMap <String, Animation> animation;
	
	int x;
	int y;
	
	int drawx;
	int drawy;
	
	int perso;
	
	
	

	
	
	
	public Game(MainGameState main) {
		super(main);
		// TODO Auto-generated constructor stub
		animation = new HashMap <String, Animation> ();
		enter = true;
	}
	
	public void renderS (GameContainer arg, StateBasedGame arg0, Graphics arg00) throws SlickException {
		
		
		
		
		if (manager.battle == null ) {   }
		else {
		// get all object to render
		ArrayList <Draw> render = new ArrayList <Draw> ();
		// get Bombs
		render.addAll ( manager.battle.getBomb  () ) ;
		// render background
		manager.battle.getBoard().renderBackground(arg00,manager, manager.getOption().gameX(), manager.getOption().gameY(),    manager.getOption().getGameWindowHeight(), manager.getOption().getGameWindowWidth());
		
		
		manager.getParticleManager().renderParticuleMiddle();
		// get client
		for (ClientPlay sp : manager.battle.players)
			if (sp == null) {   }
			else if (animation.get (sp.playerNumber + "") == null) { }
			else
				render.add (sp);
		
		render.addAll (manager.battle.getBoard().getDrawBlock());
		render.addAll (manager.battle.getBoard().getDrawDec());
		
		// sort the render
		try {
		extracted(render);
		}
		catch (java.lang.IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		
		for (int i = 0; i < render.size(); i ++) {
			render.get(i).render (arg00, arg, manager, manager.getOption().gameX(), manager.getOption().gameY(), manager.getOption().getGameWindowWidth() , manager.getOption().getGameWindowHeight(), animation);
		}
		
		
		
		
		
		
		
		
		}
		 
	}

	private void extracted(ArrayList<Draw> render) {
		Collections.sort (render);
	}
	
	public void updateS (GameContainer arg, StateBasedGame arg0, int arg00) {
		if (enter) {
			manager.getNetworkManager().getServer().ready();
			enter = false;
			stop = true;
		}
		
		
		
		
		
		
		
		
		
		
		if (manager.battle == null) {     }
		else {
		// off 2 26 47
		
		// off 4 -4 20
		
		
		// off 3 4 14
		
		checkAnimation();
		
		
		
		
		
		
		for (ClientPlay sp : manager.battle.players)
			if (sp == null) {   }
			else if (animation.get (sp.playerNumber + "") == null) { }
			else
				animation.get (sp.playerNumber + "").update(arg00);
		
		
		
		
		
		
		
		if (  manager.battle.isPlay() && enter == false  ) {
		
			if  (manager.isDown ("LEFT") && stop == false) {
				if ((direction == 4) == false)
				manager.getNetworkManager().getServer().sendDirection(4);
				direction = 4;
		}
			else if (manager.isDown("RIGTH") && stop == false) {
				System.out.println("LEFT");
				
				if ((direction == 3) == false)
				manager.getNetworkManager().getServer().sendDirection(3);
				direction = 3;
			}
			
			
			else if (manager.isDown ("DOWN") && stop == false) {
				if ((direction == 1) == false)
				
				manager.getNetworkManager().getServer().sendDirection(1);
				
				direction = 1;
				
			}
			else if (manager.isDown ("UP")     && stop == false)  {
				if ((direction == 2) == false)
					
					
				manager.getNetworkManager().getServer().sendDirection(2);
				
				direction = 2;
				
			}
			else {
				
				if ((direction == 0) == false)
			
				manager.getNetworkManager().getServer().sendDirection(0);
				
				
				direction = 0;
				
			}
			
			
			
			
			if (manager.isPressed ("SPACE"))
				manager.getNetworkManager().getServer().sendBomb ();
			
			
		
		}
		
		
		
		
		
		
		
		
		
		
		
		}
				
			
		
			
	
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 41;
	}
	
	private void checkAnimation () {
		for (ClientPlay anim : manager.battle.players)   {
			if (anim == null) {   }
			else {
			if (animation.get(anim.playerNumber + "") == null)  {
				animation.put (anim.playerNumber + "", manager.getRessource().getAnimation ("START_"   + anim.getSelect()) );
				animation.get (anim.playerNumber + "").setLooping(false);
				animation.get (anim.playerNumber + "").start();
				animation.get (anim.playerNumber + "").setSpeed (1.0f);
			}
			else if (animation.get(anim.playerNumber + "").isStopped() ) {
				stop = false;
				if (anim.direction == 0)
					animation.put (anim.playerNumber + ""   , manager.getRessource().getAnimation ("SPRITE_IDLE_1_"+ anim.getSelect() )) ;
				
				else
				animation.put (anim.playerNumber + ""   , manager.getRessource().getAnimation ("SPRITE_RUN_"  + anim.direction + "_" + anim.getSelect() )) ;
				
				animation.get (anim.playerNumber + "").start();
				animation.get (anim.playerNumber + "").setSpeed (1.0f);
				
				
			}
			else if ( isAnimationChange (anim) && ( animation.get (anim.playerNumber + "") == manager.getRessource().getAnimation ("START_" + anim.getSelect() ) ) == false ) {
				   stop = false;
				if (anim.direction == 0)
					animation.put (anim.playerNumber + ""   , manager.getRessource().getAnimation ("SPRITE_IDLE_1_"+ anim.getSelect() )) ;
				
				else
				animation.put (anim.playerNumber + ""   , manager.getRessource().getAnimation ("SPRITE_RUN_"  + anim.direction + "_" + anim.getSelect() )) ;
				
				animation.get (anim.playerNumber + "").start();
				animation.get (anim.playerNumber + "").setSpeed (1.0f);
				
			}
			}
		}
	}
	
	private boolean isAnimationChange (ClientPlay anim) {
		if (anim.direction == 0)
		return ( animation.get(anim.playerNumber + "").equals (manager.getRessource().getAnimation ("SPRITE_IDLE_1_" + "" + anim.getSelect())  ) ) == false ;
		else
		return	( animation.get(anim.playerNumber + "").equals (manager.getRessource().getAnimation ("SPRITE_RUN_" + anim.direction + "_" + anim.getSelect() ) )  ) == false;
	}
	
	public void intro () {
		if ( (animation.get(playerNumber + "") == null)     == false && animation.get(playerNumber + "") == manager.getRessource().getAnimation ("START_" + playerNumber) && animation.get (playerNumber + "" ).isStopped()  ) {
			manager.getNetworkManager().getServer().ready();
			enter = false;
		}
		if (animation.get(playerNumber + "") == null )
			animation.put (playerNumber + "", manager.getRessource().getAnimation ("START_" + playerNumber));
		
	}
	
	

	
	
}
