package com.bomberserver.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BoardLoad {

	public static Board getBoardFromFile(String p) {
		File f = new File (p);
		try {
			BufferedReader buf = new BufferedReader (new FileReader (f));
			
			try {
				
				int size = 0;
				int sizee = 0;
				for (String s = ""; (s = buf.readLine())!= null;) {
				size = size + 1;
				sizee = s.length();
				}
				Board map = new Board (sizee, size);
				buf = new BufferedReader (new FileReader (f));
				
				int [] t = new int [map.getSize()];
				int i = 0;
				for (String s = ""; (s = buf.readLine())!= null;) {
					for (int j = 0; j<s.length(); j++) {
					t [j + (s.length()*i)] = Integer.parseInt(s.substring(j, j+1));
					}
					i++;
				}
				map.setTiles(t);
				return map;
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(" Erreur dans chargement de " + f);
		return null;
	}
	
	
	
}
