package com.bombershinobi.Network;



import org.newdawn.slick.Animation;

import Objet.Battle;
import Objet.Bomb;
import Objet.ClientPlay;

import com.bombershinobi.Main.MainGameState;
import com.net.Packet.*;
import com.net.Packet;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;


public class NetworkListener extends Listener {

	private Client client;
	private MainGameState manager;

	public void init(Client client, MainGameState manager) {
		this.client = client;

		this.manager = manager;
	}

	public void connected(Connection arg) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		Log.info("[CLIENT] " + " CONNECTED   ");
		Packet0loginRequest r = new Packet0loginRequest();
		r.ip = manager.getNetworkManager().getIp();
		client.sendTCP(r);
	}

	public void disconnected(Connection arg) {
		Log.info("[CLIENT] " + " Disconected  ");
		manager.getEventManager().event03Disconnect();
	}

	public void received(Connection arg, Object objet) {
		if (objet instanceof Packet.Packet1loginAccepted) {
			System.out.println("HEHEHEEHHEE");
			if (((Packet.Packet1loginAccepted) objet).b) {
			manager.getNetworkManager().connectClient(((Packet.Packet1loginAccepted) objet).id, ((Packet.Packet1loginAccepted) objet).p);
			} else
				client.close();
		}


		else if (objet instanceof Packet2Message) {
			if (((Packet2Message) objet).battle > 0)
				((Packet2Message) objet).battle = 1;
			manager.addMessage(((Packet2Message) objet).message, ((Packet2Message) objet).battle);
		}

		else if (objet instanceof Packet20Conected) {
			String[] s = ((Packet20Conected) objet).conected.split("-");
			manager.getNetworkManager().setConected(s); 

		}
		
		else if (objet instanceof Packet202NewPlayer) {
		  	manager.battle.loadClient(((Packet202NewPlayer) objet).client);
		}
		
		else if (objet instanceof Packet25BattleCreateAccept) {
			Packet.Packet24Joinn p = new Packet.Packet24Joinn();
			p.b = ((Packet25BattleCreateAccept) objet).b;
			p.client = manager.getNetworkManager().getId();
			arg.sendTCP(p);
		}
		
		
		else if (objet instanceof Packet200JoinBack) {
			
			if (       ( (Packet200JoinBack) objet ) .accept  ) {
				
				manager.isBattleOn = true;
				manager.battleNumber = ((Packet200JoinBack) objet).b;
				manager.battle = Battle.createB( ((Packet200JoinBack) objet).battle, ((Packet200JoinBack) objet).boardBack, ((Packet200JoinBack) objet).client, ((Packet200JoinBack) objet).board, ((Packet200JoinBack) objet).boardBlock, ((Packet200JoinBack) objet).boardDec);
				manager.battle.setId   (manager.battleNumber);
				for (int i = 0; i < manager.battle.players.length; i++ )
					if (manager.battle.players [i] == null) {  }
					else
					if (manager.battle.players[i].getID() == manager.getNetworkManager().getId()) {
						manager.play = manager.battle.players[i];
						int play =  (int) ( ( (  (Packet200JoinBack) objet).playerNumber));
						manager.battle.players[i].playerNumber = play;

					}
				
			
				
				
				
				
				
				
				
			
			}
		}
		
		
		else if (objet instanceof Packet203PlayerInfo) {
			Packet203PlayerInfo o = (Packet203PlayerInfo) objet;
			if ((manager.battle == null) == false) {
			for (int i = 0; i < manager.battle.players.length; i++ ) {
				if ((manager.battle.players[i] == null) == false &&  manager.battle.players[i].getID() == o.player)   {
					manager.battle.players[i].setPosition (o.x, o.y);
					manager.battle.players[i].setDirection (o.direction);
				}
			}
			}
		}
		
		
		else if (objet instanceof Packet206PoseBomb ) {
			manager.getEventManager().event01BombPose((Packet206PoseBomb)objet);
	//		manager.getSoundManager().PoseBomb ();
	//		manager.getEffectManager().PoseBomb (((Packet206PoseBomb) objet).x, ((Packet206PoseBomb) objet).y);
		}
		
		
		else if (objet instanceof Packet28BattleSelect ) {
			manager.getEventManager().event02BattleSelect(((Packet28BattleSelect) objet).client, ((Packet28BattleSelect) objet).player);
		}
		
		
		else if (objet instanceof Packet201BattleSendS) {
			
			for (ClientPlay personnage : manager.battle.players ) 
				
				if ( personnage == null )  {  }
				else
				if (  personnage.getID() == ((Packet201BattleSendS) objet).select ) {
					if (((Packet201BattleSendS) objet).selection)
					personnage.select();
					else
						personnage.selectChange();
			
			if (((Packet201BattleSendS) objet).selection == true)
			manager.getMusicManager().playSound ("MENU_SELECT_ACCEPT_" + personnage.getSelect());
				
			
				}
		}
		
		
		else if (objet instanceof Packet206DelPlay) {
			manager.battle.loadClient (((Packet206DelPlay) objet).client);
		}
		
		else if (objet instanceof Packet29BattleStart) {
			
			manager.battle.run();
		}
		
		else if (objet instanceof Packet29BattlePlay) {
			manager.battle.play(true);
		}
		
		else if (objet instanceof Packet202BattleExplode) {
			manager.battle.explose(((Packet202BattleExplode) objet).x, ((Packet202BattleExplode) objet).y, ((Packet202BattleExplode) objet).rangeup, ((Packet202BattleExplode) objet).rangeleft, ((Packet202BattleExplode) objet).range, ((Packet202BattleExplode) objet).rangerigth ) ;
		}
		
		else if (objet instanceof Packet30BattlePlayDie) {
			manager.battle.playDie(((Packet30BattlePlayDie) objet).p);
		}
		
		
		else if (objet instanceof Packet30BattleDecExplose) {
			manager.battle.getBoard().setOneTileOfDec (((Packet30BattleDecExplose) objet).x, ((Packet30BattleDecExplose) objet).y, 0);
		}
		
		
		
	}

}
