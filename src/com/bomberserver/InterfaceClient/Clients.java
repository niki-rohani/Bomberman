package com.bomberserver.InterfaceClient;


import java.util.ArrayList;

import javax.swing.JList;


import com.bomberserver.GenericFonction.FontColor;
import com.bomberserver.GenericFonction.Tool;
import com.esotericsoftware.kryonet.Server;



public class Clients  {

	public String client;
	public Boolean show;
	public Integer clientId;
	public String name;
	public String color;
	public Server serveur;
	public int port;
	
	private ClientPlay play;
	
	
	public Clients (int c) {
		client = "";
		show = false;
		clientId = c;
		color = "";
		name = "";
		serveur = null;
		port = 0;
		play = new ClientPlay (clientId);
		
	}
	
	public Clients (String IP, int clientID, String clientName, Server s, int p, String c) {
		client = IP;
		show = true;
		clientId = clientID;
		color = c;
		name = clientName;
		serveur = s;
		port = p;
		play = new ClientPlay(clientId);
	}
	
	public ClientPlay getPlay() {
		return play;
	}

	public void setPlay(ClientPlay play) {
		this.play = play;
	}

	public void setName(String n) {
		name = n;
	}
	
	public String getName () {
		return name;
	}
	
	public String getColor () {
		return color;
	}
	
	public boolean changeColor (String c) {
		if (Tool.checkColor (c))
		 color   = c ;
		else
		return false;
		return true;
	}
	
	
	public void kickClient() {
		
			show = false;
			
			serveur.close();
			serveur.stop();
			serveur = null;
			
			
	
	}
	
	
	public String toString () {
		String s = "";
			if (show) 
			s = s + " player ip : " + client + " ID : " + clientId + " \n ";
			
		
		
		return s;
	}
	
	public boolean isShow() {
		return show;
	}
	
	
	
	
	public Server getServeur () {
		return serveur;
	}
	
	public int getID() {
		return clientId;
	}
	
	public String getip() {
		return client;
	}

	public void sendTCP(Object packet) {
		// TODO Auto-generated method stub
		serveur.sendToAllTCP(packet);
		
	}
	
	public void sendUDP(Object packet) {
		System.out.println ("serveur " + serveur);
		serveur.sendToAllUDP (packet);
	}

	public Integer getPort() {
		// TODO Auto-generated method stub
		return port;
	}
	

	
	
}
