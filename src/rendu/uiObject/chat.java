package rendu.uiObject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import rendu.TextField;
import rendu.UIAbstract;
import shout.ShoutBox;

import State.RootPane;
import Tool.Screen;

import com.bombershinobi.Main.MainGameState;

import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.textarea.HTMLTextAreaModel;

public class chat extends UIAbstract {

	
	
	private TextField field;
	private TextField field2;
	HTMLTextAreaModel textAreaModel;
	TextArea textArea;
	EditField editField;
	private ScrollPane scrollPane;
	
	
	
private int numberofmess;
	
	private String shout;
	
	private boolean goDown;
	
	private int number;
	MainGameState s;
	int id;
	
	
	public chat(int x, int y, float mul, GameContainer arg, MainGameState g, int i, RootPane pane) {
		
		super      (x, y, mul);
		this.textAreaModel = new HTMLTextAreaModel();
	    this.textArea = new TextArea(textAreaModel);
	    this.editField = new EditField();
	    
	    s = g;
	    id = i;
	    
	    editField.addCallback(new EditField.Callback() {
            public void callback(int key) { 

            	if  ( s.getCurrentStateID()  == id ) {
            	String ecrivez = "Ecrivez et appuyez sur entrer ";
            	int length = editField.getText().length();
            	if (length > 3) {
            		
            	for (int i = 0; i < length - 1; i++) {
            		
       
            		if (  ( ( (i==0) ? "" : editField.getText().substring(0, i+1) )
            				+ editField.getText().substring(i+2, length))
            				.equals(ecrivez)) {
            			  editField.setText(    editField.getText().substring(i+1, i+2)  )
            			;
            			  break;
            		}
        
            	}
            	}
            	}
            	
                if(key == Event.KEY_RETURN) {
                   
                		if (editField.getText().equals("") == false) {
        				s.getNetworkManager().send(editField.getText(), id);
        				editField.setText("");
        			} 
                	
                }
            }
            
            
        });
	    
	    

        textArea.addCallback(new TextArea.Callback() {
            public void handleLinkClicked(String href) {
                Sys.openURL(href);
            }
        });

        scrollPane = new ScrollPane(textArea);
        scrollPane.setFixed(ScrollPane.Fixed.HORIZONTAL);
        pane.add(scrollPane);
        scrollPane.setPosition(Screen.getX(arg, 0.77f), 20);
        scrollPane.setSize(Screen.getX(arg, 0.18f), Screen.getY(arg, 0.682f));
        
        
        pane.add(editField);
        
        editField.setPosition(Screen.getX(arg, 0.77f), Screen.getY(arg, 0.713f));
        editField.setSize(Screen.getX(arg, 0.2f), Screen.getY(arg, 0.048f));
          editField.setText("Ecrivez et appuyez sur entrer ");
        
   
		
		 
		
	}
	
	@SuppressWarnings("deprecation")
	public void render (Graphics g, MainGameState s, GameContainer arg) {
		
		
		
		g.drawImage( Screen.getScaled(arg, 0.8f, s.getRessource().getImage("MENU_IN_BANDE")) , Screen.getX(arg, 0.73f), - 40)  ;
		g.drawImage(Screen.getScaled(arg, 0.08f, s.getRessource().getImage("MENU_IN_CHAT"))  , Screen.getX(arg, 0.72f), Screen.getY(arg, 0.7f));
		
		
	
	}
	
	
	public void update (GameContainer arg, MainGameState s) {
		
		 
		 ArrayList <String> mess = s.getShout().getMessage(id);
		 if ((numberofmess == mess.size()) == false) {
			 for (int i = numberofmess; i < mess.size(); i ++) {  
				shout = shout + ShoutBox.appendHtml(mess.get(i), s.getShout().getColor(i, id));	
			 }
		numberofmess = mess.size();
			String ss = "";
					for (int i = 0; i < 40; i++)
						ss =    ss + "<div>  &nbsp; </div>" ;
					 textAreaModel.setHtml(  ss + shout );
					
					goDown = true;
		 }
		 if (number % 10 == 0) {
		 goDown = false;	
		  number = 1;
		 }
		 
		 
		 
		 if (goDown) {
			 scrollPane.getVerticalScrollbar().scroll(   scrollPane.getVerticalScrollbar().getMaxValue() );
			 number ++;
		 	}
		
		 
	
		
		
	}
	
	
	public Object getInfo() {
		return null;
	}
}
