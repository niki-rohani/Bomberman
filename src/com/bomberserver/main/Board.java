package com.bomberserver.main;


import java.awt.Graphics;

import com.bomberserver.InterfaceClient.ClientPlay;



public class Board {

	
	
	
	
	public int largeur, hauteur;
	
	private int[][] tiles;
	private int[][] block;
	private int [][] dec;
	public double [][] pos;
	public int [] [] bonus;
	private int size;
	
	public String name;
	
	TiledMap map;
	
	public Board (int w, int h, String s) {
		largeur = w;
		hauteur = h;
		tiles = new int [hauteur] [largeur] ;
		size = hauteur * largeur;
		name = s;
		block = new int [hauteur] [largeur];
		dec = new int [hauteur] [largeur];
		
		loadMap();
		
		BomberServer.logger.info (largeur + " " + hauteur);
	}
	
	public void loadMap () {
		map = new TiledMap (name);
		
		int background = map.getLayerIndex("background");
		int blockTile = map.getLayerIndex ("block");
		int explose = map.getLayerIndex ("explose");
		int player = map.getLayerIndex ("player");
		
		largeur = map.getWidth();
		hauteur = map.getHeight();
		tiles = new int [hauteur] [largeur];
		block = new int [hauteur] [largeur];
		dec = new int [hauteur] [largeur];
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		pos = new double [4][2];
		
		for (int i = 0; i < tiles.length; i++)
			for (int j = 0; j < tiles[0].length; j++ )    {
					tiles[i][j] = Integer.parseInt ( map.getTileProperty (map.getTileId (j, i, background), "id", "1") )     
					 ;
			if (blockTile > -1)
				  if (map.getTileId (j, i, blockTile) > 0)
				block [i][j] = Integer.parseInt ( map.getTileProperty (map.getTileId (j, i, blockTile), "id", "1") );
						else
				block [i][j] = 0;
				
			
			if (player > - 1)
			if (map.getTileId (j, i, player) > 0) {
				double    [] p = {  getPosition100 ( j, i ) [0] , getPosition100 (j, i) [1] };
				
				pos[ Integer.parseInt (  map.getTileProperty ( map.getTileId(j, i, player), "id", "1")   ) - 1] = p;
						
			}
			
			
			
			
			if (explose > - 1)
				if (map.getTileId (j, i, explose) > 0 && Math.random() > 0.1 ) {
					dec  [i][j] = Integer.parseInt ( map.getTileProperty (map.getTileId (j, i, explose), "id", "1") )   ;
					System.out.println ("dec " + i + " " + j + " " + dec [i] [j] );
					
				}
			
			}
				
		
		
		
	}
	
	
	public void renderBackground (Graphics g, int x, int y, int dx, int dy, int h, int l) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j <  tiles[0].length; j++) {
				g.drawString (tiles[i][j] + " ", i, 50);
			}
		}
	}
	
	
	public void setBlock (int[][] t) {
		block = t;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int[][] getBlock() {
		return block;
	}
	

	
	
	public int getOneTileOfDec (int x, int y) {
		return dec [y] [x];
	}
	
	public void setOneTileOfDec (int x, int y, int tile) {
		dec [y] [x] = tile;
	}
	
	
	public int [] [] getDec (int x, int y) {
		return dec;
	}
	
	public String serial() {
		String board = tiles.length + ":" + tiles[0].length + ":" + name;
		return board;
	}
	
	
	public String toString() {
		return tiles.length + ":" + tiles[0].length;
	}
	
	public String serialBlock() {
		String board = "";
		for (int i = 0; i < block.length ; i++) {
			for (int j = 0; j < block[0].length; j++)
				if (i == block.length - 1 && j == block[0].length - 1)
					board = board + block [i]   [j];
				else
			    board = board + block [i]  [j] + "_" ;
		}
		return board;
	}
	
	public String serialDec() {
		String board = "";
		for (int i = 0; i < block.length ; i++) {
			for (int j = 0; j < block[0].length; j++)
				board = board + dec [i]    [j] ;
		}
		return board;
	}

	public String serialBack() {
		String board = "";
		for (int i = 0; i < tiles.length ; i++) {
			for (int j = 0; j < tiles[0].length; j++)
				if (i == tiles.length - 1 && j == tiles[0].length - 1)
					board = board + tiles [i]   [j];
				else
			    board = board + tiles [i]  [j] + "_" ;
		}
		return board;
	}

	public boolean isLegalMove(ClientPlay play, Battle b) {
		// TODO Auto-generated method stub
		if (play.getDirection() == 1) {
			 int [] pos = getPosition (play.getX(), play.getY() + 0.5 * play.speed);
			 int [] pos0 = getPosition (play.getX(), play.getY());
			 if (pos[1] > block.length-1 || pos[0] > block[0].length - 1 || block[pos[1]][pos[0]] > 0 || dec [pos[1]] [pos[0]] > 0)
				 return false;
			 else if (       (b.isBomb (pos[0], pos[1]) == null) == false && b.isBomb(pos[0], pos[1]).equals (b.isBomb (pos0[0], pos0[1])) == false )
				 return false;
		}
		
		
		
		if (play.getDirection() == 2) {
			 int [] pos = getPosition (play.getX(), play.getY()        - 0.5 * play.speed);
			 int [] pos0 = getPosition (play.getX(), play.getY());
			 
			 if (block[pos[1]][pos[0]] > 0 || dec [pos[1]] [pos[0]] > 0)
				 return false;
			 else if (       (b.isBomb (pos[0], pos[1]) == null) == false && b.isBomb(pos[0], pos[1]).equals (b.isBomb (pos0[0], pos0[1])) == false )
						 return false;
		
		}
		
		
		
		if (play.getDirection() == 3) {
			 int [] pos = getPosition (play.getX() + 0.5 * play.speed, play.getY());
			 int [] pos0 = getPosition (play.getX(), play.getY());
			 
			 if (block[pos[1]][pos[0]] > 0  || dec [pos[1]] [pos[0]] > 0)
				 return false;
			 else if (       (b.isBomb (pos[0], pos[1]) == null) == false && b.isBomb(pos[0], pos[1]).equals (b.isBomb (pos0[0], pos0[1])) == false )
					 return false;
		
		}
		
		
		
		
		
		if (play.getDirection() == 4) {
			 int [] pos = getPosition (play.getX() - 0.5 * play.speed, play.getY()     );
			 
			 int [] pos0 = getPosition (play.getX(), play.getY());
			 
			 if (block[pos[1]][pos[0]] > 0   || dec [pos[1]] [pos[0]] > 0)
				 return false;
			 else if (       (b.isBomb (pos[0], pos[1]) == null) == false && b.isBomb(pos[0], pos[1]).equals (b.isBomb (pos0[0], pos0[1])) == false )
						return false;
		
		}
		
		
		
		
		
		return true;
	}
	
	
	public int[] getPosition(double x, double y) {
		int  [] r = new int [2];
		r[0] = (int)    (  x / ( 100.0 /   (double) largeur ) );
		r[1] = (int)   ( y / ( 100.0 / (double) hauteur )  );
		return r;
	}
	
	public double  [] getPosition100 (int x, int y) {
		double [] pos = new double [2];
		pos[0] = x * ( 100 / (double) largeur );
		pos [1] = y * ( 100 /  (double) hauteur  );
		return pos;
	}
}
