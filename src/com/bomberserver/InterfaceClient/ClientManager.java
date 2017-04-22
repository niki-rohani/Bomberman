package com.bomberserver.InterfaceClient;

import java.awt.Component;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JList;

import com.bomberserver.GenericFonction.FontColor;
import com.bomberserver.main.BomberServer;
import com.esotericsoftware.kryonet.Server;
import com.net.Packet.Packet2Message;


import server.*;





/**
 * 
 * ClientManager
 * 
 *  The client manager is a manager of client's that openned a new dedicate server.
 *  There is two HashMap for the client.
 *  The first is client. It got all the client sort by ID, wich is the ip and the port
 *  The second is port. It got all the available port, and tell you if it's used.
 *  
 *  When a client is add, it will add the client at the Key IP + P in client array.
 *  It will also put the port in array port to false.
 *  
 *  When a client is remove, it will shut the connexion and remove the client in client.
 *  it will put the port in array port to true.
 *  
 *  
 * @author Dantidot
 *
 */

public class ClientManager {

	private HashMap<String, Clients> client;
	private BomberServer manager;
	private HashMap <Integer, Boolean> port;
	public static int[] PORT = {59000,59100};

	public ClientManager(BomberServer s) {
		client = new HashMap<String, Clients>();
		manager = s;
		
		port = new HashMap <Integer, Boolean>   () ;
		for (int i = PORT [0]; i < PORT [1]; i++) {
			port.put(i, true);
		}
	}
	

	public JList getJList() {
		JList jj = new JList();
		String[] clientList = new String[client.size()];
		Object[] clients =  client.values().toArray();
		int j = 0;
		for (int i = 0; i < clients.length; i++) {
			if (((Clients) clients[i]).isShow()) {
				clientList[j] = "Player IP   " + ((Clients) clients[i]).getip()
						+ "   #     ID : " + ((Clients) clients[i]).getID();
				j++;
			}
		}
		jj.setListData(clientList);
		return jj;
	}

	public String[] getClientArray () {
		String [] clientList = new String[client.size()];
		Collection<Clients> clientV = client.values();
		Object[] clients =  clientV.toArray();
		int j = 0;
		for (int i=1; i<clients    .length ; i++) {
			if (((Clients) clients    [i]) .isShow()) {
				clientList[j] = "Player IP   " + ((Clients) clients      [i]) .getip() + "   #     ID : " +      ((Clients) clients       [i]) .getID()  ;
				j++;
			}
		}
		return clientList;
	}

	public Clients getClient(String i) {
		return client.get(i);
	}
	
	public void setColor (String i, String c) {
		client.get(i).color = c;
	}

	public boolean setName(String i, String n) {
		Collection<Clients> clientV = client.values();
		Object[] clients =  clientV.toArray();
		for (int j = 0; j < clients.length; j++)
			if (!(((Clients) clients[j]).getName() == null)
					&& ((Clients) clients[j]).getName().equals(n))
				return false;
		client.get(i).setName(n);
		return true;
	}

	public String getName(int i) {
		if (client.get(i).getName() == null)
			return "Player " + client.get(i).getID();
		else
			return client.get(i).getName();
	}

	public String getFontColor(int i) {
		return client.get(i).getColor();
	}

	public int getNumberOfConnect() {
		int numberOfConnect = 0;
		Clients[] clients = (Clients[]) client.values().toArray();

		for (int i = 1; i < clients.length; i++)
			if (clients[i].isShow())
				numberOfConnect++;
		
		
		
		return numberOfConnect;

	}
	
	public void addClient (String ip, int ID, Server serveur, int p, String c) {
		System.out.println("add client " + ip+p);
		client.put(ip+p, new Clients (ip, ID, "", serveur, p, c));
		if (client.get(ip+p) == null) {
			System.out.println("BAD ADD");
		}
		else
			System.out.println (ip+p + " ADD ");
		
		port.remove(client.get(ip+p).getPort());
		port.put(client.get(ip+p).getPort(), false);

	}
	
	public void removeClient (String ID) {
		
		  port.remove(client.get(ID).getPort());
		  port.put (client.get(ID).getPort(), true);
		  if (client.containsKey(ID) && client.get(ID) != null) {
		client.get(ID).kickClient();
		client.remove(ID);
		
		for (int i = 0; i < manager.battleRun.size(); i++ )
			for (int b = 0; b < manager.battleRun.get(i).battleInfo.players.length; b++ )    {
				if ( ( manager.battleRun.get(i).battleInfo.players[b] == null ) == false && ( manager.battleRun.get(i).battleInfo.players[b].getip() + manager.battleRun.get(i).battleInfo.players[b].getPort()  ) .equals ( ID  ) )
					manager.battleRun.get(i).removeClient(b);
			}
		
		  }
		
	}
	
	public int getPort() {
		for (int i = PORT[0]; i < PORT[1]; i++) 
			if (port.get(i) == true)
				return i;
		return 0;
	}
	

	
	

	public String toString () {
		String toString = "";
		Object[] clientv =  client.values().toArray();
		for (int j   = 0; j < clientv.length; j++ ) {
			toString = toString + (Clients) clientv[j];
		}
		return toString;
		
	}


	public void sendToAllExceptTCP(String id, Object packet) {
		Object[] clientConnected =  client.values().toArray();
		
		for (int i = 0; i < clientConnected.length; i++ )
			if (clientConnected[i].equals(client.get(id)))
				{}
			else   {   (  (Clients) clientConnected[i]  ) .sendTCP(packet)   ;  }  
	}


	
	

}
