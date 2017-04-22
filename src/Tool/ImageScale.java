package Tool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class ImageScale {

	
	
	
	
	
	public static Image getScaledImage(double sc, GameContainer c, Image i) {
		float f = (float) sc;
		return i.getScaledCopy((int) (  c.getWidth() ) , (int) (  c.getHeight() )).getScaledCopy(f)   ;
	}
}
