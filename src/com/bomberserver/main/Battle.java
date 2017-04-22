package com.bomberserver.main;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import com.bomberserver.InterfaceClient.ClientPlay;
import com.bomberserver.InterfaceClient.Clients;
import com.bombershinobi.Main.MainGameState;
import com.net.Packet;



public class Battle {

	private int playerID;
	private String[] propriete;
	public Clients[] players;
	private boolean run;
	private Board board;
	private int id;
	private String name;
	public ArrayList <Bomb> bomb;
	public int bombGenerate;
	
	public int bonusProbability;
	
	
	public ArrayList <Bonus> bonus;
	
	
	public static Battle createBattle (int player, int nombreJoueur, String boardPath, int battleID, String name ) {
		BomberServer.logger.info("creating battle " + " player " + player + " number of play " + nombreJoueur + " name " + name);
		String [] propriete = new String [2];
		propriete[0] = String.valueOf ( nombreJoueur ) ;
		propriete[1] = boardPath.split(":")[2];
		Battle battle = new Battle (player, propriete, battleID, name);
		return battle;
	}
	
	
	public Battle () {
		propriete = new String [2];
	}
	
	public Battle (int player, String[] p, int battleID, String n) {
		playerID = player;
		propriete = p;
		players = new Clients [ Integer.parseInt ( p[0] )  ];
		run = false;
		board = new Board (0, 0, "Map/" + p[1] + "/map.tmx");
		id = battleID;
		name = n;
		bomb = new ArrayList <Bomb> ();
		for (int i = 0; i < players.length; i++ )
			players[i] = null;
		
		bombGenerate = 0;
		
		
		bonusProbability = 40;
		
		bonus = new ArrayList <Bonus> ();
	}
	
	public boolean runBattle () {
		if (run = false) {
		if (getPlayer() > 1)
			 run = true;
		return run;
		}
		BomberServer.logger.info("Battle is already startet");
		return false;
	}
	
	public void restart() {
		bomb.clear();
		for (int i = 0; i < players.length; i++ )
			
		  if (players[i] == null ) {       }
		  else {
		    players[i].getPlay().restart(board.pos[players[i].getPlay().playerNumber - 1]);
		    
		   }
		
		
		bonus.clear();
		
	
	}
	
	/**
	 * Get number players that are playing
	 * @return
	 */
	public int getPlayer () {
		int p = 0;
		for (int i=0; i< players.length; i++) {
			if (players[i] != null)
				p++;
		}
		return p;
	}
	
	
	
	public Board getBoard() {
		return board;
	}
	
	public int getID() {
		return id;
	}
	
	public int getPlayerInit() {
		return playerID;
	}
	
	
	public Bonus getBonus (int x, int y) {
		for (Bonus b  : bonus) {
			if (b.getX() == x && b.getY() == y)
				return b;
		}
		return null;
	}
	
	
	public int addPlayer (Clients p) {
		for (int i=0; i < players.length; i++ )
			
				if ( !(players[i] == null) &&  players[i].equals(p))
					return 0;
				
		if (getPlayer() < players.length )
			for (int i=0; i < players.length; i++ )
				if (players[i] == null) {
					players[i] = p;
					p.getPlay().playerNumber = i + 1;
					
					int [] selection  = {1,1,1,1};
					for (int next = 0; next < players.length; next++)
						if (players[i] == null) {  }
						else
							
								selection [players[i].getPlay().getPersonnage() - 1] = 0;
				
					
					return i + 1; }
		return 0;
		}
	
	public void addBomb (Bomb b) {
		bomb.add  (b);
			
	}
	
	
	public void addBonus (int x, int y, int typ) {
		bonus.add (new Bonus (x, y, typ));
	}
	
	public boolean isRun() {
		return run;
	}
	
	
	public boolean isBonus (int x, int y) {
		for (Bonus b : bonus) {
			if (b.getX() == x && b.getY() == y)
				return true;
		}
		return false;
	}
	
	public Bomb isBomb (int x, int y) {
		for (Bomb b : bomb)
			if (b.getX() == x && b.getY() == y)
				return b;
		return null;
	}
	
	public int bombID () {
		bombGenerate++;
		return bombGenerate;
	}
	
	public void deleteBonus (int x, int y) {
		Bonus r = null;
		for (Bonus b: bonus) {
			if (b.getX() == x && b.getY() == y) {
				r = b;
			}
		}
		if (r == null)
			return;
		
		
		bonus.remove (r);
		
	}
	
	public String serial () {
		
		return playerID + ":" +     propriete [0] + ":" + propriete [1] + ":" + id + ":" + name;
	}
	
	public void load (String s1, String s2) {
		
		String        [] bat = s1.split(":");
		
		playerID = Integer.parseInt ( bat[0]  ) ;
		propriete[0] = bat [1];
		propriete  [1] = bat[2] ;  this.board = BoardLoad.getBoardFromFile(s2) ;
		id = Integer.parseInt ( bat [3]  ) ;
		name = bat[4];
	}

	public String seriaPlayers() {
		String re = "";
		for (int i=0 ; i < players.length; i++ ) {
			if (players[i] != null)
				re = re + players[i].getPlay().serial() + "-";
			else
				re = re + 0 + "-";
		}
		return re;
	}
	
	public String toString () {
		return "Battle " + id + " running = " + run + " number of player = " + getPlayer(); 
	}
	

	
	
	
}
