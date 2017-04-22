package Tool;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import Objet.Resolution;


public class Screen {

	
	
	public static ArrayList<Resolution> getListOfResolution () {
	  
		
		ArrayList <Resolution> int2D = new ArrayList <Resolution> ();
		DisplayMode[] screen2DResolution;
		try {
			screen2DResolution = Display.getAvailableDisplayModes();
			
		
		for (int i=0; i<screen2DResolution.length; i++ ) {
				Resolution r =   new Resolution ( screen2DResolution[i] ) ;
				if (  ! ( int2D.contains(r)   ) )
				int2D.add( r);

		}
		} catch (LWJGLException e) {
		}
		
		Collections.sort(int2D);
		
		return int2D;
	}
	
	public static String[] getScreenResolutionString() {
		ArrayList  <Resolution> s = getListOfResolution();
		String [] screenResolutionString = new String [s.size()];
		
		
		for(int i = 0; i < screenResolutionString.length; i++ )
			screenResolutionString [i] =  s.get(i).resolution()  ;
		
		return screenResolutionString;
	}
	
	
	
	/**
	 * Get absolute x position of the screen arg, with the relative mul
	 * @param arg  Screen
	 * @param mul  relation position MAX is 1
	 * @return
	 */
	public static int getX(GameContainer arg, float mul) {
		return  (int) ( arg.getWidth() * mul  ) ;
	}
	
	/**
	 * Get absolute y position of the screen arg, with the relative mul
	 * @param arg  Screen
	 * @param mul  relation position MAX is 1
	 * @return
	 */
	public static int getY(GameContainer arg, float mul) {
		return (int) ( arg.getHeight() * mul      );
	}
	
	
	
	
	
	public static Image getScaled (GameContainer arg, float mul, Image i) {
		double proportion = i.getWidth() / (double) i.getHeight();
		int y = arg.getHeight();
		int x =   (int)  ( y * proportion )   ;
		
		return i.getScaledCopy(x, y).getScaledCopy(mul);
	}
	
	public static Image getScaled (int h, float mul, Image i) {
		double proportion = i.getWidth() / (double) i.getHeight();
		int y = h;
		int x =   (int)  ( y * proportion )   ;
		
		return i.getScaledCopy(x, y).getScaledCopy(mul);
	}
	
	public static Image getScaledWidth (GameContainer arg, float mul, Image i) {
		double proportion = i.getWidth() / (double) i.getHeight();
		
		int x =   (int)  ( arg.getWidth()  )   ;
		
		int y = (int)    ( x /  proportion ) ;
		
		return i.getScaledCopy(x, y).getScaledCopy(mul);
	}
	
	
	public static Image getScaledWidth (int w, float mul, Image i) {
		double proportion = i.getWidth()		/ (double) i.getHeight();
		
		int x =   (int)  ( w  )   ;
		
		int y = (int)    (       x / proportion ) ;
		
		return i.getScaledCopy(x, y).getScaledCopy(mul);
	}
	
	public static Image getScaledWidth (int w, Image i) {
		return i.getScaledCopy(w, (int) (w / (i.getWidth() / (double) i.getHeight() )));
	}
	
	
	public static int centerX(float w, Image i) {
		return     (int)  (( w - i.getWidth() ) / 2.0);
	}
	
	public static int centerY(float h, Image i) {
		return (int) ((h - i.getHeight()) / 2.0);
	}
	
	
	
	
	
	
	
}
