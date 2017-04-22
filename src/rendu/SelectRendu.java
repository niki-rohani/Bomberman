package rendu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;



public class SelectRendu {

	private String[] list;
	
	
	private boolean focus;
	private int select;
	
	public SelectRendu (String[] li) {
		list = li;
		focus = false;
		select = 0;
	}
	
	public boolean isFocus() {
		return focus;
	}
	
	public void setFocus(boolean f) {
		focus = f;
	}
	
	
	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public void update (GUIContext c) {
		if (isFocus()) {
		if (c.getInput().isKeyPressed(Input.KEY_RIGHT))
			select = select + 1;
		if (c.getInput().isKeyPressed(Input.KEY_LEFT))
			select = select - 1;
		
		if (select < 0)
			select = 0;
		else if (select > list.length - 1)
			select = list.length - 1;
		}
	}
	
	public void render (GUIContext c, Graphics g, int w, int h) {
		
		
		
		
		
		g.drawString("Résolution", w, h);
		if (focus)
				g.setColor  (Color.orange);
			else
				g.setColor(Color.black);
		
	 if (select > 0)
			
			 g.drawRect(w, h + 20, 5, 3);
		
		if (list != null) {
		g.drawString(list[select], w + 30, h + 20 ) ;
		
		if (select < list.length - 1)
			
			g.drawRect(w + 10 + 10 * list[select].length() + 60, h + 20, 5, 3 );
		
		}
		
		
		
	}
	
	
}
