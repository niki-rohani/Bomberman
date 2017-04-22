package com.bombershinobi.Network;



import com.bombershinobi.Main.MainGameState;
import com.net.Packet.*;

import com.net.Packet;
import com.esotericsoftware.kryonet.Client;

public class NetworkComunic {

	private Client client;
	private MainGameState manager;

	public void send(String s, int id, int battle) {
		Packet2Message p = new Packet2Message();

		String[] color = s.split(" ");

		if (color.length > 0 && s.split(" ")[0].equals("setcolor"))
			p.message = s;
		else

			p.message = s;
		
		if (id > 0)
		p.battle = battle;
		
		
		client.sendTCP(p);
	}

	public void init(Client client, MainGameState manager) {
		this.client = client;
		this.manager = manager;
	}

	public void askConected() {
		// Packet.Packet30AskConnected p = new Packet.Packet30AskConnected();

		Packet.Packet30AskConected p = new Packet.Packet30AskConected();

		// Packet.Packet2Message p = new Packet.Packet2Message();

		client.sendUDP(p);

	}
	
	
	public int ping() {
		Packet.Packet100Ping p = new Packet.Packet100Ping ();
		client.sendTCP(p);
	}

	public void getListBattle() {
		Packet.Packet23AskBattleWait p    = new Packet.Packet23AskBattleWait();
		client.sendUDP(p);
	}
	
	public void join(int b, int client) {
		Packet.Packet24Joinn p = new Packet.Packet24Joinn ();
		p.b = b;
		p.client = client;
		this.client.sendTCP(p);
		
		
	}
	
	public void sendDirection (int gun) {
		Packet.Packet220Direction p = new Packet.Packet220Direction();
		p.battle = manager.battle.getId();
		p.player = manager.getNetworkManager().getId();
		p.direction = gun;
		
		client.sendTCP (p);
	}
	
	public void sendPoseBomb () {
		Packet.Packet206PoseBomb p = new Packet.Packet206PoseBomb();
		p.battle = manager.battle.getId();
		p.player = manager.getNetworkManager().getId();
		client.sendTCP (p);
	}
	
	public void ready () {
		Packet.Packet29Ready p = new Packet.Packet29Ready();
		p.b = manager.battle.getId();
		p.client = manager.getNetworkManager().getId();
		client.sendTCP (p);
		
	}
	
	
	public void sendBomb () {
		Packet.Packet206PoseBomb p =     new Packet.Packet206PoseBomb ();
		p.battle = manager.battle.getId();
		p.player = manager.getNetworkManager().getId();
		client.sendTCP (p);
	}
	

	public void movePlayer (int move) {
		
		
		for (int i = 0; i < manager.battle.
				players.
				length; i++ )
		if (manager.battle.players[i].getID() == manager.getNetworkManager().getId()) {
			if (     ( manager.battle.players[i].direction == move        ) == false ) {
				manager.battle.players[i].direction = move;
				Packet.Packet220Direction p = new Packet.Packet220Direction ();
				
				p.battle = manager.battle.getId();
				p.direction = move;
				p.player =  manager.getNetworkManager().getId();
				client.sendUDP    (p);
						
			}
		}
		
	}
	
	
	
	public void sendNewBattle (String battle, String board) {
		Packet.Packet22NewBattle p = new Packet.Packet22NewBattle();
	 	p.run = battle;
		p.board = board;
		p.client = manager.getNetworkManager().getId();
		Packet.Packet22NewBattle f = new Packet.Packet22NewBattle();
		client.sendTCP(p);
	}


	
	
}
