package com.bombershinobi.Manager;

import java.io.IOException;

import com.bombershinobi.Main.MainGameState;
import com.bombershinobi.Network.NetworkComunic;
import com.bombershinobi.Network.NetworkListener;
import com.esotericsoftware.kryonet.Client;
import com.net.register;

public class NetworkManager {

	
	
	private Client client;
	private int port;
	private String ip;
	private int id;
	private boolean connected;
	private NetworkComunic server;
	private String stringID;
	private String  [] conected;
	
	
	public NetworkManager () {
		client = new Client();
		register.register(client, 2);
		NetworkListener listener = new NetworkListener();
		listener.init(client, MainGameState.CONTROLLER);
		client.addListener(listener);
		client.start();
		server = new NetworkComunic();
		server.init(client, MainGameState.CONTROLLER);
		connected = false;
		stringID = ip+port;
		conected = null;
	}
	
	
	public Client getClient() {
		return client;
	}

	public int getId() {
		return id;
	}
	

	public void setClient(Client client) {
		this.client = client;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isConnected() {
		return client.isConnected();
	}


	public NetworkComunic getServer() {
		return server;
	}

	public void setServer(NetworkComunic server) {
		this.server = server;
	}
	
	
	
	
	public void connectClient(int id, int p) {
		
		
		
		
		this.id = id;
		port = p;
		
		client.close();
		client.stop();
		client = new Client(200000, 200000);
		
		NetworkListener listener = new NetworkListener();
		listener.init(client, MainGameState.CONTROLLER);
		
		client.addListener (listener);
		client.start();
		register.register(client, 0);
		
		
		server.init(client, MainGameState.CONTROLLER);
		try {
			client.connect(5000, MainGameState.CONTROLLER.getOption().getIp(), p, p) ;
			
		} catch (IOException e) {
			System.out.println("ERREUR" + e);
		}
		
		
		stringID = ip+port;
		
		
		
	}
	
	




	/**
	 * Connect : Let you connect to the server, with the option.ip and
	 * option.port (cf Option) Se connecte au serveur, en prenant l'ip et le
	 * port dans option (cf Option)
	 * 
	 * @return
	 */
	public boolean connect() {

		try {
			Thread.sleep(30);
			client.connect(5000,  MainGameState.CONTROLLER.getOption().getIp(),    MainGameState.CONTROLLER.getOption().getPort(),      MainGameState.CONTROLLER.getOption().getPort());
			ip = client.getRemoteAddressTCP().getAddress().getHostAddress();
			connected = client.isConnected();
			return client.isConnected();
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		} catch (InterruptedException e) {
		}
		return false;
	}	
	
	/**
	 * Let you know the client ip Retourne l'ip client
	 * 
	 * @return the client ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Called if the client is disconnected after being connect Est appelé si le
	 * client est déconnecté après être connecté
	 */
	public void deconected() {
		
	  
		connected = false;
		client = new Client();
	
	
	}

	
	/**
	 * Ask the list of connected client in the server Demande au serveur la
	 * liste des connectés
	 */
	public void getConected() {
		server.askConected();

	}
	
	
	public void setConected(String[] conected) {
		this.conected = conected;
	}

	public String[] getConectedL() {
		if (conected == null) 
			return new String[0];
		return conected;
	}

	
	/**
	 * Send a mess to the server (Shout box) Envoi un message au serveur
	 * 
	 * @param s
	 *            : Message you want to send
	 */
	public void send(String s, int id) {
		if (MainGameState.CONTROLLER.battle == null)
			server.send (s, id, 0);
		else 
		server.send(s, id, MainGameState.CONTROLLER.battle.getId() + 1);
		
		
		
	
	}

	
}
