package rendu;


import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

public class TextFieldOption extends TextField {

	
	
	public Color colr;
	public int cursor;
	public TrueTypeFont fonth ;
	public String t;
	
	public TextFieldOption(GUIContext container, TrueTypeFont font, int x, int y,
			int width, int height) {
		super(container, font, x, y, width, height);
		colr = Color.orange ;
		cursor = 0;
		fonth = font;
		t = "";
		
	}
	
	public TextFieldOption(GUIContext container, TrueTypeFont font, int x, int y,
			int width, int height, ComponentListener l) {
		super(container, font, x, y, width, height, l);
		colr = Color.orange ;
		fonth = font;
		
		
		t = "";
		
		setBackgroundColor (null);
		
	}
	
	
	
	
	
	public void setText (String s) {
		String ss = "" ;
		for (int i = 0; i < s.length(); i ++)
			if ( s.charAt(i) == ' ') {
			
			}
			else { ss = ss + s.charAt(i); }
		super.setText(ss);
		
	}
	

	
	
	
	
	
	
	public String getText () {
		String ss = "" ;
		for (int i = 0; i < super.getText().length(); i ++)
			if ( super.getText().charAt(i) == ' ') {
			
			}
			else { ss = ss + super.getText().charAt(i); }
		return ss;
	}
	
	public void setCursorPosition (int pocur) {
		cursor = pocur;
	}
	
	

	
}
