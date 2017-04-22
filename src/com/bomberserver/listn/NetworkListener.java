package com.bomberserver.listn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;




import com.bomberserver.GenericFonction.*;
import com.bomberserver.InterfaceClient.ClientManager;
import com.bomberserver.InterfaceClient.Clients;
import com.bomberserver.main.Battle;
import com.bomberserver.main.BattleTh;
import com.bomberserver.main.Bomb;
import com.bomberserver.main.BomberServer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.net.Packet;
import com.net.Packet.*;

public class NetworkListener extends Listener {

	BomberServer manager;
	ClientManager clientManager;
	private int pListen;
	private String ipListen;
	private Server client;
	private Thread checkLaunch;

	public NetworkListener (BomberServer server, int p, String ip, Server s) {
		manager = server;
		clientManager = server.getClientManager();
		pListen = p;
		
		ipListen =   ip;
		
		client = s;
		
	}

	public void connected (Connection arg) {
		if (pListen != manager.getPropriete().getPort()) 
			
		Log.printLoggerInfo(Log.CONNECTION + " Port " + pListen  );
		
		if (   manager.checkBan ( arg.getRemoteAddressTCP().getHostString())  ) {
			Log.printLoggerInfo("BAN");
			arg.close();
		}
	}

	public void disconnected (Connection arg) {
	
		if (pListen != manager.getPropriete().getPort()) {
			
		Log.printLoggerInfo(Log.DISCO[0] + arg.getID() + Log.DISCO[1]);
		
		Packet.Packet2Message p = new Packet.Packet2Message();
		p.message = " [ player " + 
		clientManager.
		getClient(ipListen + pListen).
		getID() + "]" +
				" s'est deconnecte éred";
		
		manager.removeClient(
				ipListen + pListen
				, arg);
		
		
		}
		
	}


	public boolean bash(String[] s, Connection arg,      Object objet) {
		
		InetSocketAddress sockettcp = arg.getRemoteAddressTCP();
		InetSocketAddress socketudp = arg.getRemoteAddressUDP();
		 
		Clients remoteHost = clientManager.getClient(sockettcp.getHostString()+pListen);
		
	 if (s[0].equals("/quit")) { clientManager.removeClient(sockettcp.getHostString()+pListen); }
	 
	 if (s[0].equals("setcolor")) {
		 
			if (s.length == 2) {
				if (Tool.checkColor (s[1])) {
					manager.getClientManager().setColor(sockettcp.getHostString()+pListen,
							((Packet.Packet2Message) objet).message.split(" ")[1]);
					((Packet.Packet2Message) objet).message = "La couleur est " + s[1] + "éred";
					arg.sendTCP(objet);
					return true;
				}
				return true;
			}
			else {
				((Packet.Packet2Message) objet).message = "Trop d'argumentséred";
				arg.sendTCP(objet);
				return true;

			}

		}
		
		if (s[0].equals("setname")) {
			if   (s.length == 2) {
				if (manager.getClientManager().setName(sockettcp.getHostString()+pListen, s[1])) {
					((Packet.Packet2Message) objet).message = "Votre nom est changééred";
					arg.sendTCP(objet);
					return true;
				}
				else {
					((Packet.Packet2Message) objet).message = "Ce nom existe, veuillez en choisir un différentéred";
					arg.sendTCP(objet);
					return true;
				}
				
			}
			
		
			
			
			else {
				((Packet.Packet2Message) objet).message = "Veuillez entrer un nom sans caracère spécialéred";
				arg.sendTCP(objet);
				return true;
			}
			
	}
		return false;
	}

	public void received (Connection arg,  Object objet) {
		InetSocketAddress sockettcp = arg.getRemoteAddressTCP();
		InetSocketAddress socketudp = arg.getRemoteAddressUDP();
		 
		Clients remoteHost = clientManager.getClient(sockettcp.getHostString()+pListen);
		String id = sockettcp.getHostString()+pListen;
		
		
		
		// Si le packet est un login Request
		if (objet instanceof Packet.Packet0loginRequest) {
			if (pListen == manager.getPropriete().getPort()  ) {
			
				int p =  manager.getClientManager().getPort();
			Server s = manager.createServer(p, sockettcp.getHostString());
			Packet1loginAccepted r = new Packet1loginAccepted();
			r.b = true;
			r.id = arg.getID();
			r.p = p;
			arg.sendTCP(r);
			manager.addClient (r.id, FontColor.getRandom(), sockettcp.getHostString(), s, r.p);
			Packet.Packet2Message packet = new Packet.Packet2Message();
			packet.message = " [ player " + r.id + "] s'est connecte éred";
			manager.getClientManager().sendToAllExceptTCP(sockettcp.getHostString() + r.id, packet);
			
		}
			}



		// Si le packet est un message
		else if (objet instanceof Packet.Packet2Message) {
			com.net.Packet.Packet2Message r = (com.net.Packet.Packet2Message)objet;
			BomberServer.logger.info((new StringBuilder("[MESSAGE] ")).append(r.message).toString());
			String [] s = ((Packet.Packet2Message) objet).message.split(" ");
			if (s.length > 0 ) 	
				if (bash (s, arg, objet)) {}
				else {
					
					((Packet.Packet2Message) objet).message  = " [ " +   ( (remoteHost.getName().equals("") ) ? ( " P " + remoteHost.getID() ) : ( remoteHost.getName() )  )  + " ] says : \n " + ((Packet.Packet2Message) objet).message + "é" + remoteHost.getColor();
					if (((Packet.Packet2Message) objet).battle == 0) manager.getClientManager().sendToAllExceptTCP("",objet);
					else
						manager.getBattleId (((Packet.Packet2Message) objet).battle - 1).sendInfoTCP (objet);

				}
		}










		else if (objet instanceof Packet.Packet30AskConected) {
			Packet20Conected r = new Packet20Conected();
			r.conected = "" ;
			arg.sendTCP(r);

		}

		
		
		else if (objet instanceof Packet.Packet22NewBattle) {
			BomberServer.logger.info("Creating Battle For Client " + remoteHost.getID() + " From Network " + pListen);
			
			int i = Tool.createBattle(manager, (Packet22NewBattle) objet);
			Packet.Packet25BattleCreateAccept p = new Packet.Packet25BattleCreateAccept ();
			p.b = i;
			BomberServer.logger.info ("Battle is create");
			arg.sendTCP(p);
		}
		
		else if (objet instanceof Packet.Packet23AskBattleWait) {
		 	Packet.Packet21BattleWait p = new Packet.Packet21BattleWait();
			p.battle = "";
			p.client = "";
			BattleTh[] b = manager.battleListWait();
			
			for (int bi = 0; bi < b.length; bi++ ) {
				p.battle = p.battle + b[bi].getBattleInfo().serial() + "#";
				
				p.client = p.client + b[bi].getBattleInfo().seriaPlayers() + "#";
				
			}
			
			client.getConnections()[0].sendTCP(p); 


	}
		
		else if (objet instanceof Packet.Packet24Joinn) {
			manager.logger.info("Someone is entering battle number " + ((Packet.Packet24Joinn) objet).b );
			double place = manager.addPlayerBattle(((Packet.Packet24Joinn) objet).client, ((Packet.Packet24Joinn) objet).b, id);
			if ( place > 0) {
				Packet.Packet200JoinBack p = new Packet.Packet200JoinBack ();
				p.accept = true;
				p.b = ((Packet.Packet24Joinn) objet).b;
				 p.battle = manager.getBattleId(((Packet.Packet24Joinn) objet).b).battleInfo.serial();
				 p.client = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.seriaPlayers();
				 p.boardBack = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.getBoard().serialBack();
				 p.boardBlock = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.getBoard().serialBlock();
				 p.boardDec = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.getBoard().serialDec();
				 p.playerNumber = place;
				 p.board = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.getBoard().serial();
					System.out.print (p.boardBlock);
				 arg.sendTCP (p);
				 
				   
				manager.logger.info("The client " + ( (Packet.Packet24Joinn) objet) .client + " is enterring the battle " + p.client  );
				Packet.Packet202NewPlayer newp = new Packet.Packet202NewPlayer();
				newp.client = manager.getBattleId (((Packet.Packet24Joinn) objet).b).battleInfo.seriaPlayers();
				
				manager.getBattleId (((Packet.Packet24Joinn) objet).b).sendInfoTCP (newp);
				
				
			}
			else {
				Packet.Packet200JoinBack p = new Packet.Packet200JoinBack ();
				p.accept = false;
				p.b = ((Packet.Packet24Joinn) objet).b;
  
			
				arg.sendTCP (p);
				manager.logger.info("The client " + ( (Packet.Packet24Joinn) objet) .client + " cannot enter "  );
				
			}
			
				
		}
		
		
		
	
		else if (objet instanceof Packet.Packet220Direction) {
			BomberServer.logger.info (" DIRECTION " + ((Packet.Packet220Direction) objet).battle + ((Packet.Packet220Direction) objet).player  );
			Clients c = Tool.getPlayer (((Packet.Packet220Direction) objet).battle, ((Packet.Packet220Direction) objet).player, manager.battleRun);
			if (c == null) {   }
			else
			if (antiThieft(c.getip() + c.getPort(), id, arg ) )
				c.getPlay().changeDirection (((Packet.Packet220Direction) objet).direction) ;
			
		}
		
		else if (objet instanceof Packet.Packet205RunBattle ) {
			
			BattleTh battle = Tool.getBattle(((Packet.Packet205RunBattle) objet).b, manager.battleRun );
			
			if (((Packet.Packet205RunBattle) objet).p == battle  .battleInfo.getPlayerInit() ) {
				battle.start();
				BomberServer.logger.info ("Launching battle " +	((Packet.Packet205RunBattle) objet).b   );
			
			}
			
				
		}
		
		
		else if (objet instanceof Packet.Packet28BattleSelect) {
			System.out.println ("Client " + ((Packet.Packet28BattleSelect) objet).client + " request select battle " + ((Packet.Packet28BattleSelect) objet).battle);  
			Battle battle = manager.getBattleId(((Packet.Packet28BattleSelect) objet).battle).battleInfo ;
					if (battle == null) {
						arg.close();
						return;
					}
					
					int selection = -1;
					for (Clients client : battle.players) {
						if ((client == null  ) == false && client.getID() == (( Packet.Packet28BattleSelect)  objet ).client) {
							 selection = ( client.getPlay().getPersonnage() +  ((Packet.Packet28BattleSelect) objet).player ) % 5;
							 
							 if (client.getPlay().isReady())
								 return;
						}
					}
					for (int i = 0; i <  battle.players.length ; i++) {
						Clients client = battle.players[i];
						if (client == null || client.getPlay() == null  ) { }
						else {
						if (client
								.getPlay().
								getPersonnage() == selection 
								&&   ( client.getID() == 
								(( Packet.Packet28BattleSelect) 
										objet ).client) == false  )
						selection = (  selection + ((Packet.Packet28BattleSelect) objet).player )  ;
						if (selection > battle.players.length)
							selection = 1;
						else if (selection < 1)
								selection = 4;
						
						if (i == battle.players.length - 1)
							if (selection == battle.players[0].getPlay().getPersonnage() )
								return;
					}
				}
					
					for (Clients client : battle.players) {
						if (client == null ) {  }
						else
						if (   client.getID() == (( Packet.Packet28BattleSelect)  objet).client  )
						client.getPlay().setCharac ( selection )  ;
						
					}
					
					((Packet.Packet28BattleSelect) objet).player = selection;
					
					
					      manager.getBattleId (((Packet.Packet28BattleSelect) objet).battle) .sendInfoTCP (objet);
					
				}
		
		else if (objet instanceof Packet.Packet201BattleSendS) {
			  if (	manager.getBattleId(((Packet.Packet201BattleSendS) objet).battle).getPlayerID(((Packet.Packet201BattleSendS) objet).select).getPlay().ready(((Packet.Packet201BattleSendS) objet).selection) )
				manager.getBattleId(((Packet.Packet201BattleSendS) objet).battle).sendInfoTCP((Packet.Packet201BattleSendS) objet);
				
			    boolean l = true;
			    int off = 0;
			  for (int i = 0; i < manager.getBattleId(((Packet.Packet201BattleSendS) objet).battle).battleInfo.players.length ; i++ )
				  if (manager.getBattleId(((Packet.Packet201BattleSendS) objet).battle).battleInfo.players[i] == null) {  }
				  else {
				  l = l && manager.getBattleId (((Packet.Packet201BattleSendS) objet).battle).battleInfo.players[i].getPlay().isReady();
				  off ++;
				  }

				  System.out.println (l + " " + off);
			  if (l && off > 1 && checkLaunch == null   )   {
				  
				   checkLaunch = new launch (manager.getBattleId (((Packet.Packet201BattleSendS) objet).battle) );
				   checkLaunch.start(); 
				   
			  }
			  else if   ( (l && off > 1)  == false ) {
				 
				  if ( ( checkLaunch ==  null  ) == false )
				  checkLaunch.stop();
				  checkLaunch = null;
				  
			  }
			  
			  }

		else if (objet instanceof Packet.Packet29Ready) {
			manager.getBattleId (((Packet.Packet29Ready) objet).b).getClientId(((Packet.Packet29Ready) objet).client).ready();
		}
		
		
		else if (objet instanceof Packet.Packet206PoseBomb)
			manager.getBattleId (((Packet.Packet206PoseBomb) objet).battle).addBomb (((Packet.Packet206PoseBomb) objet).player);
		
		
	}
	
	public boolean antiThieft (String id, String ip, Connection arg) {
		if (id.equals ( ip )  )
			return true;
		arg.close();
		return false;
	}
	
	
	public class launch extends Thread {
		
		
		   BattleTh isStill;
		
		launch (BattleTh b) {
			isStill = b;
		}
		public void run () {
			
	  		int i = 0;
	  		while ((i > 10) == false) {
	  			
	  		boolean l = true;
	  		for (int j = 0; j < isStill.battleInfo.players.length; j++)     
	  			if (isStill.battleInfo.players[j] == null ) { }
	  			else
				  l = l &&        isStill.battleInfo.players [j].getPlay().isReady(); 
				  
	  		
			  
	  		  i++;
	  		
	  		 try {
	  			 sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		
	  		 
	  		 Packet2Message p = null;
	  		 if (l == false){
	  			 p = new Packet2Message();
	  		 p.message = "Annulation du lancementéred";
	  		 p.battle = 1;
	  		 isStill.sendInfoTCP(p);
	  		 destroy();
	  		}
	  		else {
	  		
	  			
	  			
	  			p = new Packet2Message ();
	  			p.message = "Lancement dans " +  ( 10 - i  ) + " secondeséred ";
	  			p.battle = 1;
	  			isStill.sendInfoTCP(p);
	  			
	  		
	  		}
	  }
	  		   
	  		 
	  		
	  	
	  		   if (isStill.isRun() == false)
	  		   
	  		   isStill.start();
	  		   
	  		   isStill.play();
		}
	}
	
}

