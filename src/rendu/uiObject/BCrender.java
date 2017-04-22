package rendu.uiObject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import rendu.UIAbstract;

import Objet.Battle;
import State.RootPane;
import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;

public class BCrender extends UIAbstract {

	

	private Font f;
	EditField nom;
	EditField joueur;
	private int select;
	TrueTypeFont ttf;
	
	
	public BCrender(int x, int y, int w, int h, RootPane rootPane) {
		
		super      (x, y, 0);
		nom = new EditField();
		joueur =  new EditField();
		
	        nom.setSize(400, 100);
			  nom.setBorderSize(2);
			  nom.setText("BATTLE OF DANTIDOT");
			  joueur.setText("10");
			  joueur.setSize(100, 30);
		  
        rootPane.add(nom);
        rootPane.add(joueur);
	    
		 
		
	}
	
	@SuppressWarnings("deprecation")
	public void render (Graphics g, MainGameState s, GameContainer arg) {
		
		Rectangle r = g.getWorldClip();
		g.setWorldClip(positionX - 20, positionY,   Screen.getX(arg, 0.24f), Screen.getX(arg, 0.25f));
		g.setColor(Color.black);
		
		int x = positionX + Screen.getX(arg, 0.02f);
		int y = positionY + 22;
		
		
			g.setFont (ttf);
		
			
			if (ttf == null)
			{
				
			}
			else {
		g.drawString(
				s.getManager().getText("CREATEBATTLE"),
				positionX + 2 + ( Screen.getX(arg, 0.22f)  - 2  ) / 2 -  
				g.getFont().
				getWidth(
						
						s.getManager().getText("CREATEBATTLE")
						
						) / 2, y - 20  );
		g.drawString(s.getManager().getText("CREATEBATTLE_NAMEGAME"), x, y + 10);
		nom.setPosition(x , y + Screen.getY(arg, 0.02f));
		y = y + Screen.getY(arg, 0.1f);
		g.drawString(s.getManager().getText("CREATEBATTLE_NUMBER"), x, y);
		
		joueur.setPosition(x, y + Screen.getY(arg, 0.02f));
		Image fl = s.getRessource().getImage("MENU_FLECHE").copy();
		fl.rotate (-90);
		g.drawImage (fl, x + 50 , y + Screen.getY(arg, 0.2f));
		fl = s.getRessource().getImage("MENU_FLECHE").copy();
		fl.rotate (90);
			
			g.drawImage (fl, x + 50 + Screen.getX(arg, 0.1f), y + Screen.getY(arg, 0.2f));
		g.drawString (s.getManager().getArene(select).getName  () , x + Screen.getX(arg, 0.05f), y  + Screen.getY(arg,  0.17f) );
		try {
			g.drawImage (Screen.getScaled (arg, 0.1f, new Image (s.getManager().getArene(select).getImage())), x + 50 + 20, y + Screen.getY(arg, 0.2f));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		g.setWorldClip  (r);
		

	}
	
	public void update (GameContainer arg, MainGameState s) {
		if (f == null)
			try {
				f = Font.createFont(Font.PLAIN, s.getManager().getFile("FONT")).deriveFont(20);
				ttf = new TrueTypeFont (f, true);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (hasFocus()) {
		if (s.isPressed("RIGTH"))
			if (select + 1 > s.getManager().getListArene().size() - 1)
				select = s.getManager().getListArene().size() - 1;
			else
			select ++;
		
		if (s.isPressed("LEFT"))
			if (select - 1 < 0)
				select = 0;
			else
			select --;
		
		
		if (select < 0)
			select = 0;
		
		if (select > s.getManager().getListArene().size())
			select = s.getManager().getListArene().size() - 1;
		
		
		if (s.isPressed("ACCEPT"))  {
			String name = nom.getText();
			int number;
			if  ( joueur.getText().equals ("") )
				 number = 4;
			else
			 number = Integer.parseInt ( joueur.getText() ) ; 
			
			Battle b = Battle.createBattle(s.getNetworkManager().getId(), number,   s.getManager().getArene(select).m, name);
			s.setBattleWaiting(b);
			s.battleWait = true;
		}
		

		if (s.isBattleWait()) {
				String battle = s.getBattleWaiting().serial();
				s.newBattle(battle, s.getBattleWaiting().getBoard().name);
				s.battleWait = false;
		}
		}
		
	}
	
	
	public Object getInfo() {
		return null;
	}
	
	
	public void focus() {
		super.focus();
		
		nom.setEnabled(true);
		joueur.setEnabled(true);
	}
	
	public void unfocus() {
		super.unfocus();
		
		nom.setEnabled(false);
		joueur.setEnabled(false);
	}
	
	public void setVisible() {
		super.setVisible();
		nom.setVisible(true);
		joueur.setVisible(true);
	}
	
	public void setInVisible() {
		super.setInVisible();
		nom.setVisible(false);
		joueur.setVisible(false);
	}
	
}
