package com.bomberserver.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;




public class Propriete {
	private File option;
	private int ClientMax = 6;
	private String nom = "Bomber Serv";
	private int port = 50805;
	
	
	public Propriete () {
		option = new File ("serv.txt");
		if (!option.exists()) {
			if (!createOptions()) throw new RuntimeException ("Erreur dans la creation de fichier");
		}
		
		loadOption();
	}
	
	public boolean createOptions() {
		try {
			PrintWriter w = new PrintWriter (new FileWriter (option));
			w.println ("nom :" + nom);
			w.println("client max :" + ClientMax);
			w.println("server port :" + port);
			w.close();
			return true;
		} catch (IOException e) {
			System.out.println("Erreur dans la creation de fichier");
			return false;
		}
		
	}
	
	public void loadOption() {
		if (!option.exists())
			return;
		try {
			BufferedReader buf = new BufferedReader (new FileReader (option));
			
			try {
				for (String s = ""; (s = buf.readLine())!= null;) {
					String [] sp = s.split(":");
					
					if (sp[0].equals("nom "))
						nom = sp[1];
					else 
						if (sp[0].equals("client max "))
							ClientMax =   Integer.parseInt(sp[1]);
						else
							if (sp[0].equals("server port "))
								port = Integer.parseInt(sp[1]);
					
				}
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName () {
		return nom;
	}
	
	public int getClientMax () {
		return ClientMax;
	}
	
	public int getPort() {
		return port;
	}
}
