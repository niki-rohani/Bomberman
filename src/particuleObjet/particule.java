package particuleObjet;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class particule {

	
	
	protected ParticleSystem syst;
	protected ConfigurableEmitter emit;
	public static int ID = 0;
	private int id;
	
	
	public particule   (float x, float y, File file, Image i) {
		try {
			emit = ParticleIO.loadEmitter(file);
			emit.setPosition(   x ,  y );
			syst = new ParticleSystem(i);
			id = ID;
			ID++;
			syst.addEmitter (emit);
			syst.setBlendingMode (ParticleSystem.BLEND_ADDITIVE);
		}
		catch (IOException e) {
			emit = null;
			syst = null;
			System.out.println ("Problem to init particule " + ID);
			ID++;
		}
	
			
	}
	
	
	
	public void setRangeX(int x, int y) {
		ConfigurableEmitter.Range donteatbad = emit.xOffset;
		donteatbad.setMin (x);
		donteatbad.setMax (y);
		emit.xOffset =   donteatbad ;
	}
	

	public void setRangeY (int x, int y) {
		ConfigurableEmitter.Range donteatbad = emit.yOffset;
		donteatbad.setMin (x);
		donteatbad.setMax (y);
		emit.yOffset =   donteatbad ;
	}
	
	public void setBlendingMode (boolean blending) {
		if (blending)
		syst.setBlendingMode (ParticleSystem.BLEND_ADDITIVE);
		else
		syst.setBlendingMode (ParticleSystem.BLEND_COMBINE);
	}
	
	public void render () {
		syst.render();
	}
	
	public void update (int up) {
		syst.update(up);
	}
	
	
	
	
	
}
