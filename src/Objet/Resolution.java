package Objet;

import java.util.Comparator;

import org.lwjgl.opengl.DisplayMode;

public class Resolution implements Comparable  <Resolution> {

	
	
	private int w;
	private int h;
	
	
	public Resolution (int res, int hh) {
		w = res;
		h = hh;
	}
	
	public Resolution (DisplayMode resolution) {
		w = resolution.getWidth();
		h = resolution.getHeight();
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight () {
		return h;
	}
	
	
	
	public String resolution() {
		return w + "x" + h;
	}

	
	
	public int compareTo (Resolution r) {
		
		
		if (getWidth() == r.getWidth() && getHeight() == r.getHeight())
			return 0;
		if (getWidth() > r.getWidth())
			return -1;
		else
			return 1;
	}
	
	
	}
