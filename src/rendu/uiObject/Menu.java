package rendu.uiObject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import rendu.UIAbstract;

import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

public class Menu extends UIAbstract {

	
	
	private  boolean animateUP, animateDOWN  ;
	private int step;
	private float rotate;
	private int positionab, positionor;
	private Image image;
	private int select;
	private int menu;
	private TrueTypeFont f;
	private int bx;
	private int by;
	private boolean bAnim;
	private int distX;
	private int distY;
	
	
	
	
	public Menu(int x, int y, float mul) {
		
		super      (x, y, mul);
		animateUP = false;
		animateDOWN =  false;
		step = 0;
		rotate = 20;
		positionab = x;
		positionor = y;
		
		menu = 4;
		select = 1;
		
		
		 
		
	}
	
	@SuppressWarnings("deprecation")
	public void render (Graphics g, MainGameState s, GameContainer arg) {
		
		
		
		if (f == null)
			try {
				 f = new TrueTypeFont ( Font.createFont(Font.TRUETYPE_FONT, s.getRessource().getFile("FONT")).deriveFont(30f), true);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} ;
		if (image == null) {
			image = Screen.getScaled(arg, size, s.getRessource().getImage("MENU_SHARINGAN"));
			
			bx = positionX + image.getWidth();
			by = positionY - Screen.getY(arg, 0.14f + 0.04f) ;
			
			distX =  positionX + image.getWidth() + Screen.getX(arg, 0.1f) ;
			distY = Screen.getY(arg,  0.2f);
		}
		 Image i   = null ;
		
		 Image band = Screen.getScaled(arg, size * 1.5f, s.getRessource().getImage("MENU_SHARIN_BANDE")    ) ;
		 band.rotate(-300);
		 g.drawImage(  band , bx, by);
		g.setFont(f);
		 
	
	
	
		 image.rotate(rotate);
		
			g.drawImage (     image , positionab, positionor )  ;
			
			if (animateUP == false)
				 if (animateDOWN == false)
				 
				 {
			i =	Screen.getScaled(arg, size * 0.555f, s.getRessource().getImage("MENU_SHARINGAN_SHIN") ) ;
			
				 }
			
			
	if((i == null) == false)
		  if (hasFocus())
		g.drawImage(i, positionX + image.getWidth() * 0.567f, positionY + image.getHeight() * 0.238f)  ;
		
	g.rotate(0,0, -28);
	
	if (animateUP == false)
		if (animateDOWN == false)
	 g.drawString(s.getRessource().getText("MENU_TITRE_" + select), positionab + image.getWidth() * 0.48f , positionor  + image.getHeight() * 0.4f);
	
	
	
	g.rotate (0,0, 28);
	}
	
	
	public void update (GameContainer arg, MainGameState s) {
		
		rotate = 0;
		if (hasFocus()) {
		if (s.isPressed("DOWN"))
			if (animateUP == false && animateDOWN == false) {
				animateDOWN = true;
				select--;
			}
		if (s.isPressed("UP")) 
			if (animateDOWN == false     && animateUP ==  false    ) {
				animateUP = true;
				select++;
			}
		if (animateUP || animateDOWN && step < ( 1 /  Math.abs (3.0) ) * 120 ) {
			step++;
			if (bAnim == false) {
				bx = bx - ( (int) ( distX / (  ( ( 1 / Math.abs(3.0) ) * 120  ) / 2.0 ) )  ) ;
				by = by + ( (int) ( distY / (  ( ( 1 / Math.abs(3.0) ) * 120 ) / 2.0 ) ) ) ;
			}
			if (bx <   - Screen.getX(arg, 0.10f))
				bAnim = true;
			
			if (bAnim == true)  {
				bx = bx + ( (int) ( distX / ( (  ( 1/3.0 ) * 120  ) / 2.0 ) )  ) ;
				by = by - ( (int) ( distY / (   ( ( 1 / 3.0 ) * 120 ) / 2.0 ) ) ) ;
			}
			
			if (bx > positionX + image.getWidth()) {
				bx = positionX + image.getWidth();
			}
			

			if (animateUP)
				rotate = - 03.0f;
			
			else if (animateDOWN)
				rotate =  03.0f;
			else
				rotate = 0;
			
		}
		else {
			
			step = 0;
			
			bAnim = false;
			
			bx = positionX + image.getWidth();
			by = positionY - Screen.getY(arg, 0.14f + 0.04f) ;
		}
		
		
			
			
		
		
	
		if (  step % ( (int) ( ( 1 / Math.abs(rotate) ) * 120  ) ) == 1 - 1  )
		{
			animateUP = false;
			animateDOWN = false;
		}
		
		if (select < 1 || select > menu)
			if (select < 1)
				select = menu;
			else
				select = 1;
		
		
		}
		
		
	}
	
	
	public Object getInfo() {
		return select;
	}
}
