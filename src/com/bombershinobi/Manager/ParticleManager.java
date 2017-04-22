package com.bombershinobi.Manager;

import java.util.ArrayList;
import java.util.HashMap;

import particuleObjet.particule;

public class ParticleManager {

	
	
	
	
	
	
	
	private HashMap <String, ArrayList <particule>> particuleSyst;
	
	
	public ParticleManager () {
		particuleSyst = new HashMap <String, ArrayList <particule>>  ();
		particuleSyst.put ("FRONT", new ArrayList <particule> ()  );
		particuleSyst.put ("MIDDLE", new ArrayList <particule> () );
		particuleSyst.put ("BACK", new ArrayList <particule> () );
		
	}
	

	
	public void addParticuleFront (particule s) {
		particuleSyst.get("FRONT").add (s);
	}
	
	public void addParticuleMiddle   (particule s) {
		particuleSyst.get("MIDDLE").add (s);
	}
	
	public void renderParticuleFront () {
		for (int i = 0; i < particuleSyst.get("FRONT").size(); i++) 
			particuleSyst.get("FRONT").get(i).render();
	}
	
	
	public void renderParticuleMiddle () {
		for (int i = 0; i < particuleSyst.get("MIDDLE").size(); i++) 
			particuleSyst.get("MIDDLE").get(i).render();
	}
	
	
	public void renderParticuleBack () {
		for (int i = 0; i < particuleSyst.get("BACK").size(); i++) 
			particuleSyst.get("BACK").get(i).render();
	}
	
	public void nextParticule(int s) {   
		for (int i = 0; i < particuleSyst.get("FRONT").size(); i++ ) {
			particuleSyst.get("FRONT").get(i).update(s);
		}
		for (int i = 0; i < particuleSyst.get("MIDDLE").size(); i++ ) {
			particuleSyst.get("MIDDLE").get(i).update(s);
		}
		for (int i = 0; i < particuleSyst.get("BACK").size(); i++ ) {
			particuleSyst.get("BACK").get(i).update(s);
		}
	}
	
	
	

	public void clearParticule () {
		particuleSyst.get("FRONT").clear();
		particuleSyst.get("MIDDLE").clear();
	}
	
	
	
	
	
	
}
