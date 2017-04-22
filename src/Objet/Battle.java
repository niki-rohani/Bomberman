package Objet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.tiled.TiledMap;

import particuleObjet.chidoParticle;
import particuleObjet.particule;

import com.bombershinobi.Main.MainGameState;

public class Battle {

	private int playerID;
	private String[] propriete;
	public ClientPlay[] players;
	private boolean run;
	private Board board;
	private int id;
	private String name;
	private boolean play;
	private TiledMap map;
	
	
	private ArrayList <Bomb> bomb;
	
	
	private ArrayList <Bonus> bonus;
	
	
	

	
	// Propiete :  1 Nombre Joueur      2 board p
	
	
	public static Battle createBattle(int player, int nombreJoueur,
			String boardPath, String name) {
		String[] propriete = new String[2];
		propriete[0] = String.valueOf(nombreJoueur);
		propriete[1] = boardPath;
		Battle battle = new Battle(player, propriete, name);
		battle.addPlayer(player);
		battle.players[0].setCharac(1);
		battle.board = new Board (0, 0, boardPath);
		return battle;
	}
	
	public static Battle createB (String b, String boardBack, String c, String board, String boardblock, String boardDec) {
	
		String[] bat = b.split(":");
		
		String [] propriete = new String [2];
		int playerID;
		playerID = Integer.parseInt(bat[0]);
		propriete[0] = bat[1];
		propriete[1] = bat[2];
		Battle battle = new Battle (playerID, propriete, bat[4]);
		
		battle.loadClient(c);
		
		battle.loadBoard(board);
		
		battle.loadBoardBlock(boardblock);
  //		
		battle.loadBoardDec (boardDec);
		
		battle.loadBoardBack (boardBack);
		
		
		
		return battle;
		
	}
	
	

	public Battle(int player, String[] p, String n) {
		playerID = player;
		name = n;
		propriete = p;
		players = new ClientPlay[Integer.parseInt(p[0])];
		players[0] = new ClientPlay(player);
		run = false;
		id = 0;
		play = false;
		bomb = new ArrayList <Bomb>     ();
		
		bonus = new ArrayList <Bonus> ();
		}

	public int getPlayer() {
		int p = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null)
				p++;
		}
		return p;
	}

	public Board getBoard() {
		return board;
	}
	
	public void play (boolean b) {
		play = b;
	}
	
	public boolean isPlay() {
		return play;
	}
	
	public int getNumberActivePlayer() {
		int	i = 0;
		for (ClientPlay client : players)
			if (client == null) {  }
			else i ++;
		return i;
	}
	
	public ClientPlay getPlayerNumber (int i) {
		for (ClientPlay client : players)
			if (client == null) {  }
			else if (client.playerNumber == i)
				return client;
		return null;
	}

	/**
	 * addPlayer to the Battle
	 * 
	 * @param player
	 *            : id
	 * @return : return true if the player is added
	 */
	public boolean addPlayer(int player) {
		if (getPlayer() < players.length)
			for (int i = 0; i < players.length; i++)
				if (players[i] == null) {
					players[i] = new ClientPlay(player);
					return true;
				}
		return false;
	}
	
	public void addBomb (Bomb b) {
		bomb.add (b);
	}
	
	public ArrayList <Bomb> getBomb () {
		return bomb;
	}
	
	
	
	
	public void delete (int x, int y) {
		for (Bomb explode : bomb) {
			if (explode.getXX () == x && explode.getYY() == y)
				bomb.remove (explode);
		}
	}
	


	
	public boolean isBomb (int x, int y) {
		for (Bomb b : bomb) {
			if (b.getXX() == x && b.getYY() == y)
				return true;
		}
		return false;
				
	}

	public boolean isRun() {
		return run;
	}

	public String serial() {
		
		return playerID + ":" + propriete[0] + ":" + propriete[1] + ":" + id  + ":" + name ; 
	}
	
	

	
	
	
	public void load(String s1, String s2, String b) {
		loadBoard(s1);

		String[] client = s2.split("-");

		for (int i = 0; i < client.length; i++) {
			String[] clientTab = client[i].split(":");
			for (int j = 0; j < clientTab.length; j++) {
				players[j] = new ClientPlay(0);
				players[j].load(clientTab);
			}
		}

		String[] bat = b.split(":");

		playerID = Integer.parseInt(bat[0]);
		propriete[0] = bat[1];
		propriete[1] = bat[2];
		id = Integer.parseInt  ( bat [3] )  ;
		name = bat [4];
	}
	
	

	public void loadClient(String s) {
		String [] client = s.split ("-");
		for (int i = 0; i < client.length ; i++) {
			players[i] = new ClientPlay(1);
			if (client [i].equals ("0") == false )
			players[i].load( client[i].split (":") )  ;
			else
			players[i] = null;
		}
	}
	
	
	public String getMap () {
		return propriete[1];
	}
	
	public void run() {
		run = true;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void loadBoard (String s) {
		String [] boardString = s.split (":");
		board = new Board (Integer.parseInt ( boardString[1] ), Integer.parseInt (boardString [0])  , boardString [2]  );
	
	}

	public void loadBoardBlock(String s) {
		int [] [] block = new int[board.getH()][board.getL()] ;
		
		String  [] sp = s.split ("_");
		if ((board.getH() * board.getL() == sp.length)   == false ) 
			return;
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block[0].length; j++  ) {
				block[i][j] = Integer.parseInt(sp [ i*block[0].length + j ]);
		}
		this.board.setBlock(block);
	}
	
	public void loadBoardBack(String s) {
		int [] [] b = new int[board.getH()][board.getL()] ;
		
		String  [] sp = s.split ("_");
		if ((board.getH() * board.getL() == sp.length)   == false ) 
			return;
		
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[0].length; j++  )
				b[i][j] = Integer.parseInt(     sp [ i*b[0].length + j ]);
		
		this.board.setBack(b);
	}
	
	public void loadBoardDec (String s) {
		int [] [] block = new int[board.getH()][board.getL()] ;
		
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block[0].length; j++  )
				block[i][j] = Integer.parseInt(String.valueOf(s.charAt( i*block[0].length + j )));
		this.board.setDec(block);
	}
	
	
	public void explose (int x, int y, int rangeup, int rangeleft, int range, int rangerigth) {
		
		
		int w;
		int h;
		int offx;
		int offy;
		
		w = MainGameState.CONTROLLER.getOption().getGameWindowWidth();
		h = MainGameState.CONTROLLER.getOption().getGameWindowHeight();
		offx = w / board.getL();
		offx = offx / 2;
		
		offy = h / board.getH();
		offy = offy / 2;
		
		
		for (Bomb b  : bomb) {
			if (b.getXX() == x && b.getYY() == y  )   {
				particule p = null;
				if (rangeleft > 0) {
					p = new chidoParticle((int) b.getX(w) + MainGameState.CONTROLLER.getOption().gameX() ,    MainGameState.CONTROLLER.getOption().gameY()   +  (int) b.getY(h) + offy + offy   / 2, MainGameState.CONTROLLER.getRessource() ) ;
					p.setRangeX(0, - rangeleft * ( offx * 2));
					MainGameState.CONTROLLER.getParticleManager().addParticuleMiddle (p) ;
					p.setRangeY (0, 0);
				}
				if (range > 0) {
					p = new chidoParticle((int) b.getX(w) + MainGameState.CONTROLLER.getOption().gameX() + offx ,   MainGameState.CONTROLLER.getOption().gameY()   +  (int) b.getY(h) + offy * 2, MainGameState.CONTROLLER.getRessource() ) ;
					p.setRangeX(0, 0);
					MainGameState.CONTROLLER.getParticleManager().addParticuleMiddle (p) ;
					p.setRangeY (0, range * (offy * 2));
				}
				if (rangerigth > 0) {
					p = new chidoParticle((int) b.getX(w) + MainGameState.CONTROLLER.getOption().gameX() ,    MainGameState.CONTROLLER.getOption().gameY()   +  (int) b.getY(h) + offy + offy   / 2, MainGameState.CONTROLLER.getRessource() ) ;
					p.setRangeX(0,    rangerigth * ( offx * 2));
					MainGameState.CONTROLLER.getParticleManager().addParticuleMiddle (p) ;
					p.setRangeY (0, 0);
				}
				if (rangeup > 0) {
					p = new chidoParticle((int) b.getX(w) + MainGameState.CONTROLLER.getOption().gameX() +   offx ,    MainGameState.CONTROLLER.getOption().gameY()   +  (int) b.getY(h) + offy + offy   / 2, MainGameState.CONTROLLER.getRessource() ) ;
					p.setRangeX(0, 0);
					MainGameState.CONTROLLER.getParticleManager().addParticuleMiddle (p) ;
					p.setRangeY (0,  -rangeup * (offy * 2) );
				}
				
				p = new chidoParticle ((int) b.getX(w) + MainGameState.CONTROLLER.getOption().gameX() + offx,           MainGameState.CONTROLLER.getOption().gameY()   + (int) b.getY(h)   + offy , MainGameState.CONTROLLER.getRessource() )  ; 
				p.setRangeX (-offx, offx);
				p.setRangeY (-offy, offy);
				MainGameState.CONTROLLER.getParticleManager().addParticuleMiddle  (p);
				
				bomb.remove(b);
				
				return;
				
			
			
			
			}
			
		}
	}
	
	
	public void playDie (int p) {
		for (ClientPlay dead : players) {
			if (dead == null)  {   }
			else if (dead.getID() == p) {
				dead.dead();
			}
			}
		}
	

}
