package Objet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Arene {

	
	
	private String name;
	private String image;
	 public String m;
	
	
	public Arene (String nam, String im, String map) {
		name = nam;
		image = im;
		m = map;
	}
	
	public String getName () {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	
	
}
