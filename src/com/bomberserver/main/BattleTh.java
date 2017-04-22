package com.bomberserver.main;


import java.util.ArrayList;

import com.bomberserver.InterfaceClient.ClientPlay;
import com.bomberserver.InterfaceClient.Clients;
import com.net.Packet;



/**
 * Classe permettant de faire tourner un battle
 * @author Dantidot
 *
 */

public class BattleTh extends Thread {
	
	// Les infos de = client;
		
	public Battle battleInfo;
	public BomberServer bomber;
	public int threadID;
	public boolean run;
	private  boolean end;
	private boolean play;
	private boolean battleIn;
	private boolean terminate;
	
	
	
	public BattleTh (Battle battle, BomberServer manags, int battleID) {
		battleInfo = battle   ;
		bomber = manags;
		threadID = battleID;
		run = false;
		end = false;
		play = false;
		terminate = false;
	}
	
	
	
	
	public void run () {
		
		while (terminate == false) {

			
			if (play == false) {
				boolean r = true;
			for (Clients client : battleInfo.players)
				if (client == null) {   }
				else
			if ( client.getPlay().isReadyToFigth())
				r = r && true;
			else
				r = false;
			
			if (r) {
				playBattle();
			}
			}
			
		if (play && end == false) {
			
					
			checkGame();
			
		
		
		
	
		
		
		}
		else if (end)
		{
			
			BomberServer.logger.info ("END BATTLE" );
			battleInfo.restart();
			end = false;
		
			 
		//	bomber.endGame (this);
		//	terminate = true;
			
		}
		run = true;
		
		
		for (int i = 0; i < battleInfo.players.length; i++) {
			if (battleInfo.players[i] == null) {
				
			}
			else
			{
			 if (battleInfo.players[i].getPlay().isAlive()) {
			Packet.Packet203PlayerInfo p = new Packet.Packet203PlayerInfo () ;
			p.x = battleInfo.players[i].getPlay().getX( );
			p.y = battleInfo.players[i].getPlay().getY ( );
			p.battle = battleInfo.getID();
			p.direction = battleInfo.players[i].getPlay().getDirection();
			p.player = battleInfo.players[i].getID();

				
			sendInfoUDP (p);
			 }
			}
		}
			try {
					sleep (30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		
		
		
		
		}
		
		
	}
	
	
	public void play() {
		Packet.Packet29BattleStart p = new Packet.Packet29BattleStart();
		sendInfoTCP (p);
	}
	
	public void playBattle () {
			for (Clients battle : battleInfo.players) {
				if (battle == null) {  }
				else
				battle.getPlay().setPosition (battleInfo.getBoard().pos[battle.getPlay().playerNumber - 1]);
			}
			play = true;
			Packet.Packet29BattlePlay p = new Packet.Packet29BattlePlay();
			sendInfoTCP (p);
		
	}
	
	
	
	public Battle getBattleInfo() {
		return battleInfo;
	}




	public void setBattleInfo(Battle battleInfo) {
		this.battleInfo = battleInfo;
	}




	public boolean isRun() {
		return run;
	}




	public void setRun(boolean run) {
		this.run = run;
	}



	

	public int getThreadID() {
		return threadID;
	}
	
	
	public Clients getPlayerID(int id) {
		for (Clients play : battleInfo.players)
			if (play == null) {  }
			else
			if (play.getID() == id)
				return play;
		return null;
	}
	
	public int getBattleID() {
		return battleInfo.getID();
	}
	
	int explodeRange (int x, int y, ArrayList <Bomb> exp, int poser) {
		if ( battleInfo.getBoard().getBlock()[y]  [x] > 0) {
			return 1;
		}
		if ( ( battleInfo.isBomb (x, y) == null ) == false && exp.contains(battleInfo.isBomb (x, y)) == false  ) {
			explodeBomb(battleInfo.isBomb (x, y), exp );
			return 1;
		}
		if  ( battleInfo.getBoard().getDec(0, 0) [y] [x] > 0)  {
			exploseDec (x, y);
			return 2;
		}
		if (battleInfo.isBonus (x, y)) {
			deleteBonus(x, y);
		}
			
		for (Clients bomb : battleInfo.players) {
			if (bomb == null) {   }
			else {
			int pos [] = battleInfo.getBoard().getPosition( bomb.getPlay().getX(), bomb.getPlay().getY()  );
			if (pos[0] == x && pos[1] == y) {
				bomb.getPlay().explode();
				Packet.Packet30BattlePlayDie p = new Packet.Packet30BattlePlayDie ();
				p.p = bomb.getID();
		   // sendInfoTCP (p);
				getPlayerID(poser).getPlay().addEnergie(800);
			}
			
			}
			
		}
			
	    return 0;
	}
	
	public void explodeBomb (Bomb b, ArrayList <Bomb> exp) {
		
		int rangeup =     (int) b.getPort();
		int rangedown =   (int) b.getPort();
		int rangerigth =    (int) b.getPort();
		int rangeleft =        (int) b.getPort();
		
		exp.add (b);
		 
		for (int range = 1; range < b.getPort() + 1; range++) {
			
			int explod = 0;
			if (rangeleft == b.getPort()) {
				explod = explodeRange (b.getX() -range, b.getY(), exp, b.Poser()  );
				 if (  explod == 1 ) rangeleft = range - 1 ;
				 else if (explod == 2) rangeleft = range;
				 
			}
			
			if (rangeup == b.getPort()) {
				explod = explodeRange (b.getX(), b.getY() - range, exp, b.Poser())  ;
				if (   explod == 1 ) rangeup = range - 1;
				else if (    explod == 2) rangeup = range;
			}
			if (rangedown == b.getPort()) {
				explod = explodeRange (b.getX(), b.getY() + range, exp, b.Poser());
				if ( explod == 1 ) rangedown = range - 1;
				else if (explod == 2) rangedown = range;
			}
			
			
			if (rangerigth == b.getPort()) {
				explod = explodeRange (b.getX()  + range   , b.getY(), exp, b.Poser()) ;
				if (   explod == 1 ) rangerigth = range - 1;
				else if (explod == 2) rangerigth = range;
			}
			
			
			
		}
		
			Packet.Packet202BattleExplode p = new Packet.Packet202BattleExplode ();
			p.ev = 1;
			p.x = b.getX();
			p.y = b.getY();
			p.rangeup = rangeup;
			p.range = rangedown;
			p.rangerigth = rangerigth;
			p.rangeleft = rangeleft;
			sendInfoTCP (p);
			
			
			 getPlayerID (b.Poser()).getPlay().deleteBomb()  ;
			
			
		
	}
		
	
	public void checkGame() {
		int dead = 0; 
		
		
		
		
		for (int i = 0; i < battleInfo.players.length; i++) {
			if (battleInfo.players[i] == null) {
				
			}
			else
			{
				if (battleInfo.players[i].getPlay().isAlive())
			 dead++;
			 battleInfo.players[i].getPlay().move(battleInfo.getBoard().isLegalMove (battleInfo.players[i].getPlay(), battleInfo));
			 int [] pos = battleInfo.getBoard().getPosition (battleInfo.players[i].getPlay().getX(), battleInfo.players[i].getPlay().getY());
			 if (battleInfo.isBonus (pos[0], pos[1]))  {     
					 getBonus (pos[0], pos[1], battleInfo.players[i].getPlay());
			}
			}
		}
			
		
	if (dead == 1 )
			end = true;
		ArrayList <Bomb> exp = new ArrayList <Bomb> ();
		try {
		for (Bomb b: battleInfo.bomb) {
			 b.update();
			if (b.isExplode()) {
				 explodeBomb (b, exp);
			}
		}
		}
		catch ( java.util.ConcurrentModificationException e) {
		}
		
		
		battleInfo.bomb.removeAll (exp);
		
		
		
	
		
		
		
}

	
	
	public void sendInfoUDP (Object p) {
		for (Clients player : battleInfo.players) {
			if (player == null)
			{ }
			else
			{
				
			   Clients c = bomber.getClientManager().getClient( player.getip() + player.getPort() ) ;
			   if (c == null ) {
				  
			   }
			   else
				   c.sendUDP (p);
				
			}
			}
	}
	
	
	public void sendInfoTCP (Object p) {
		for (Clients player : battleInfo.players) {
			if ((player == null) == false )
			bomber.getClientManager().getClient( player.getip() + player.getPort() ).sendTCP(p) ;
		}
	}
	
	
	public void removeClient (int i) {
	
		Packet.Packet206DelPlay p = new Packet.Packet206DelPlay();
		p.play = battleInfo.players[i].getID();
			battleInfo.players[i] = null;		
	
			p.client = battleInfo.seriaPlayers();
		sendInfoTCP (p);
		
	}
	
	public ClientPlay getClientId (int id) {
		for (Clients client : battleInfo.players)
			if (client.getID() == id)
				return client.getPlay();
		return null;
	}
	
	
	
	
	
	
	

	
	
	public int addPlayer (Clients battleTh) {
		  return battleInfo.addPlayer(battleTh);
	}
	
	
	public void addBomb (int p) {
		  if (play) {
		int []   r = battleInfo.getBoard().getPosition ( getPlayerID (p).getPlay().getX(),      getPlayerID (p).getPlay().getY());
		
		Bomb b = new Bomb (r[0], r[1] , getPlayerID (p).getPlay().getSelection() , getPlayerID (p).getPlay().getPw(), battleInfo.bombID(), p);
		
		
		if (battleInfo.isBomb (r[0], r[1]) == null && getPlayerID (p).getPlay().getBomb() > 0) {
		battleInfo.addBomb (b);
		
		
		Packet.Packet206PoseBomb pbomb =   new Packet.Packet206PoseBomb ();
		
		pbomb.playerCharacter =   getPlayerID (p).getPlay().getSelection() ;
		pbomb.x =  battleInfo.getBoard().getPosition100 ( b.getX(), b.getY() ) [0]  ;
		pbomb.y =     battleInfo.getBoard().getPosition100 ( b.getX(), b.getY() ) [1] ;
		
		pbomb.tileX = b.getX();
		pbomb.tileY = b.getY();
		sendInfoTCP (pbomb);
		getPlayerID(p).getPlay().addBombPose();
		
		
				
		
		}
		  
		  }
	}
	
	
	
	
	public void exploseDec(int x, int y) {
		battleInfo.getBoard().setOneTileOfDec(x, y, 0);
		Packet.Packet30BattleDecExplose p = new Packet.Packet30BattleDecExplose ();
		p.x = x;
		p.y = y;
		sendInfoTCP (p);
		if (Math.random() * 100 < battleInfo.bonusProbability) {
			int b =  (int) (Math.random() * 4) + 1;
			battleInfo.bonus.add (new Bonus (b , x, y));
			Packet.Packet30BattleAddBonus pbonus = new Packet.Packet30BattleAddBonus ();
			pbonus.x = x;
			pbonus.y = y;
			pbonus.b = b;
			sendInfoTCP (p);
		}
	}
	
	public void deleteBonus   (int x, int y) {
		battleInfo.deleteBonus (x, y);
		
	}
	
	
	public void getBonus (int x, int y, ClientPlay p) {
		int bonn = battleInfo.getBonus (x, y).bonus();
		if (bonn == Bonus.POWER)
			p.addPw();
		if (bonn == Bonus.SPEED)
			p.addSpeed ();
		if (bonn == Bonus.CHACRA)
			p.addEnergie(10);
		if (bonn == Bonus.BOMB)
			p.addBomb();
		
		
		deleteBonus(x, y);
	}

}
