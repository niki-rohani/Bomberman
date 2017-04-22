package Objet;





import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.bombershinobi.Main.MainGameState;

public class Board {

	
	
	
	private int largeur, hauteur;
	
	private int[][] tiles;
	private int[][] block;
	private int [][] dec;
	
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
		
		
		
	}

	
	public int[] getPosition(double x, double y) {
		int  [] r = new int [2];
		r[0] = (int)    (  x / ( 100.0 /   (double) largeur ) );
		r[1] = (int)   ( y / ( 100.0 / (double) hauteur )  );
		return r;
	}
	
	public double  [] getPosition100 (int x, int y) {
		double [] pos = new double [2];
		pos[0] = x * ( 100.0 / (double) largeur );
		pos [1] = y * ( 100.0 /  (double) hauteur  );
		return pos;
	}
	
	
	public void renderBackground (Graphics g,    MainGameState manager, int x, int y, int h, int l) {
	  
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j <  tiles[0].length; j++) {
				if (tiles [i] [j] > 0)
				g.drawImage  (manager.getRessource().getImage ("FLOOR" + tiles[i][j])
						
						.getScaledCopy (  getCaseWidth (l) , getCaseHeight(h) ), (int) ( x + getCaseWidth(l) * j ), y +    getCaseHeight       (h) * i)    ;
				
				}
		}
	}
	
	
	public ArrayList <Block> getDrawBlock () {
		ArrayList <Block> b = new ArrayList <Block> ();
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block[0].length;  j++)
				if (block[i][j] > 0)
					b.add (new Block (block[i][j], getPosition100(j, i)[0], getPosition100(j, i) [1] + ( 100 / (double) getH()) - 1 ));
		return b;
	}
	
	
	public ArrayList <Decor> getDrawDec () {
		ArrayList <Decor> b = new ArrayList <Decor> () ;
		for (int i = 0; i < dec.length; i++)
			for (int j = 0; j < dec[0].length;  j++)
				if (dec[i][j] > 0)
					b.add (new Decor ( getPosition100(j, i)[0], getPosition100(j, i) [1] + ( 100 / (double) getH()) - 1, dec [i][j] ));
		return b;
	}
	
	
	
	
	
	public void setBlock (int[][] t) {
		block = t;
	}
	
	public void setBack (int[][] t) {
		tiles = t;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int[][] getBlock() {
		return block;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i<tiles.length; i++ )
			for (int j = 0; j < tiles[0].length; j++)
				s = s   + tiles[i][j];
			
		return s;
		
	}
	
	public int getOneTileOfBlock (int x, int y) {
		return block[y] [x]  ;
		}
	
	public void setOneOfBlock (int x, int y, int tile) {
		block [y]  [x] = tile;
	}
	
	public int getOneTileOfDec (int x, int y) {
		return dec [y] [x];
	}
	
	public void setOneTileOfDec (int x, int y, int tile) {
		dec [y] [x] = tile;
	}
	
	public String serial() {
		String board = largeur + ":" + hauteur + ":" + name;
		return board;
	}
	
	public String serialBlock() {
		String board = "";
		for (int i = 0; i < block.length ; i++) {
			board = board + block [i];
		}
		return board;
	}
	
	public String serialDec() {
		String board = "";
		
		return board;
	}

	public int getH() {
		// TODO Auto-generated method stub
		return hauteur;
	}
	
	public int getL() {
		return largeur;
	}

	public void setDec(int[][] block2) {
		dec = block2;
		
	}
	
	
	public int getCaseWidth (int game) {
		return (int)  (game / (double) largeur);
	}
	
	public int getCaseHeight (int game) {
		return (int) (game / (double) hauteur);
	}
	


	
}
