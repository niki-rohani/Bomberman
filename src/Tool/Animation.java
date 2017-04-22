package Tool;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Animation {

	private int step;
	private int maxStepToEnd;
	private int xd , yd;
	private double xf  , yf   ;
	private double x  ; 
	private double y    ;
	private double xx  ;
	private double yy  ; private boolean enterze;
	private Image i;
	
	public Animation (Image anim, int maxStep, double positionX, double positionY, int mul) {
		i = anim;
		maxStepToEnd = maxStep;
		x = i.getWidth();
		y = i.getHeight();
		xd =  (int) x;
		yd =  (int  ) y;
		xf = xd * mul;
		yf = yd * mul;
		step = 0;
		xx =  positionX;
		yy = positionY;
	
		
	}
	
	public void render (Graphics g) {
		g.drawImage(i.getScaledCopy( (int) x,  (int) y), (float) xx, (float) yy);
		
	}
	
	public void update () {
		if (! ( step > maxStepToEnd )  ) {
			
		xx = xx     - ( (xf - xd )       / maxStepToEnd ) / 2 ;
		yy = yy - ( (yf - yd ) /  maxStepToEnd ) /  2 ;
		

		x = x + ( xf - xd ) / maxStepToEnd;
		y = y + ( yf -  yd ) / maxStepToEnd;
		
		step ++;
		}
	}
	
	
	public int getStep() {
		return step;
	}
	
	public int getMaxStepToEnd() {
		return maxStepToEnd;
	}
	
	
	  
	public boolean isAnimationDone() {
		return (step==maxStepToEnd);
	}
}
