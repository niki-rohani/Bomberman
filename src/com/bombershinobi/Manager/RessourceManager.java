package com.bombershinobi.Manager;


/**
 * @author Dantidot
 * Classe Permettant de gérer les ressources
 * Chargement des ressource a travers un fichier XM.
 * Type de ressources,
 * image
 * animation
 * son
 * text
 */
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Objet.Arene;
import Tool.LoadingInfo;

public class RessourceManager {
 
	private static String SPRITE_SHEET_REF = "__SPRITE_SHEET_";
 
	private static RessourceManager _instance = new RessourceManager();
 
	private Map<String, Sound> soundMap;
	private Map<String, Image> imageMap;
	private Map<String, Animation> animationMap;
	private Map<String, String> textMap;
	private Map <String, File> xmlFile;
	private ArrayList <Arene> arene;
 
	public RessourceManager(){
		soundMap 	 = new HashMap<String, Sound>();
		imageMap 	 = new HashMap<String, Image>();
		animationMap = new HashMap<String, Animation>();
		textMap 	 = new HashMap<String, String>();
		xmlFile = new HashMap <String, File> ();
		arene = new ArrayList ();
	}
 
	public final static RessourceManager getInstance(){
		return _instance;
	}
 
	public void loadResources(InputStream is) throws SlickException {
		loadResources(is, false, new LoadingInfo());
	}
 
	public void loadResources(InputStream is, boolean deferred, LoadingInfo info) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		Document doc = null;
        try {
			doc = docBuilder.parse (is);
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}
 
		// normalize text representation
        doc.getDocumentElement ().normalize ();
 
        NodeList listResources = doc.getElementsByTagName("resource");
 
        int totalResources = listResources.getLength();
        
        
        
        	LoadingList.setDeferredLoading(deferred);
        
      
 
 
        for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){
        	
        	Node resourceNode = listResources.item(resourceIdx);
 
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;
 
        		String type = resourceElement.getAttribute("type");
        		
        		if(type.equals("image")){
        			addElementAsImage(resourceElement, info);
        		}else if(type.equals("sound")){
        			addElementAsSound(resourceElement, info);
        		}else if(type.equals("text")){
        			addElementAsText(resourceElement, info);
        		}else if(type.equals("font")){
 
        		}else if(type.equals("animation")){
        			addElementAsAnimation(resourceElement, info);
        		}
        		else if (type.equals("file")) {
    
        			addElementAsFile(resourceElement, info);
        		}
        		else if (type.equals("arene")) {
        				addElementAsArene(resourceElement, info, docBuilder);
        			}
        		
        		
        		info.setNext(info.getNext() + 1);
        			
        	}
        	
        }
 
	}
	
	
	

	private void addElementAsArene(Element resourceElement, LoadingInfo info, DocumentBuilder docBuilder) throws SlickException {
		
		
		Document doc = null;
        try {
			doc = docBuilder.parse (new FileInputStream (resourceElement.getTextContent()));
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}
 
		// normalize text representation
        doc.getDocumentElement ().normalize ();
 
        NodeList listResources = doc.getElementsByTagName("resource");
 
        int totalResources = listResources.getLength();
        
       for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){
        	
        	Node resourceNode = listResources.item(resourceIdx);
 
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element r = (Element)resourceNode;
 
        		String nom = r.getAttribute("nom");
        		String image = r.getAttribute ("image");
        		String matrice = r.getAttribute ("matrice");
        		
        		
        		arene.add(new Arene(nom, image, matrice));
        	
        		
        	}
       }
        	
        
        
		
	}

	public Arene getArene (int id) {
		return arene.get(id);
	}
	
	public ArrayList <Arene> getListArene () {
		return ( ArrayList <Arene> ) arene.clone()  ;
	}
	
	public void addElementAsFile (Element r, LoadingInfo info) {
		loadFile (r.getAttribute("id"), r.getTextContent());
	}
	
	public void loadFile (String id, String t) {
		    System.out.println();
		xmlFile.put(id, new File (t));
	}
	
	public File getFile (String id) {
		return xmlFile.get(id);
	}
	
	private void addElementAsAnimation(Element resourceElement, LoadingInfo info) throws SlickException{
		loadAnimation(resourceElement.getAttribute("id"), resourceElement.getTextContent(), 
				Integer.valueOf(resourceElement.getAttribute("tw")),
				Integer.valueOf(resourceElement.getAttribute("th")),
				Integer.valueOf(resourceElement.getAttribute("duration")), info);
	}
 
	private void loadAnimation(String id, String spriteSheetPath,
			int tw, int th, int duration, LoadingInfo info) throws SlickException{
		if(spriteSheetPath == null || spriteSheetPath.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");
 
		loadImage( SPRITE_SHEET_REF + id, spriteSheetPath, info);
		ResourceAnimationData rad =  new ResourceAnimationData(SPRITE_SHEET_REF+id, tw, th, duration);
		 
		SpriteSheet spr = new SpriteSheet(getImage(rad.getImageId()), rad.tw, rad.th);
 
		Animation animation = new Animation(spr, rad.duration);
 
		
		animation.stop();
		
		animationMap.put(id, animation);
		
	}

 
	public final Animation getAnimation(String ID){
		Animation animation = animationMap.get(ID);
 
		
		return animation;
	}
 
	private void addElementAsText(Element resourceElement, LoadingInfo info) throws SlickException{
		loadText(resourceElement.getAttribute("id"), resourceElement.getTextContent(), info);
		
	}
 
	public String loadText(String id, String value, LoadingInfo info) throws SlickException{
		if(value == null)
			throw new SlickException("Text resource [" + id + "] has invalid value");
 
		textMap.put(id, value);
		
		return value;
	}
 
	public String getText(String ID) {
		return textMap.get(ID);
	}
 
	private void addElementAsSound(Element resourceElement, LoadingInfo info) throws SlickException {
		loadSound(resourceElement.getAttribute("id"), resourceElement.getTextContent(), info);
	}
 
	public Sound loadSound(String id, String path, LoadingInfo info) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id + "] has invalid path");
 
		Sound sound = null;
 
		try {
			sound = new Sound(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}
 
		this.soundMap.put(id, sound);
		
		return sound;
	}
 
	public final Sound getSound(String ID){
		return soundMap.get(ID);
	}
 
 
	private final void addElementAsImage(Element resourceElement, LoadingInfo info) throws SlickException {
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent(), info);
	}
 
	public Image loadImage(String id, String path, LoadingInfo info) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");
 
		Image image = null;
		try{
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}

		this.imageMap.put(id, image);

		return image;
	}
 
	public final Image getImage(String ID){
		return imageMap.get(ID);
	}
 
 
 
 
	public void clear() {
		animationMap.clear();
		textMap.clear();
		Image i = imageMap.get("INTRO");
		imageMap.clear();
		imageMap.put("INTRO", i);
		soundMap.clear();
		xmlFile.clear();
	}
 
 
 
 
	private class ResourceAnimationData{
		int duration;
		int tw;
		int th;
		String imageId;
 
		public ResourceAnimationData(String id, int tw, int th, int duration){
			this.imageId = id;
			this.tw = tw;
			this.th = th;
			this.duration = duration;
		}
 
		public final int getDuration() {
			return duration;
		}
		public final void setDuration(int duration) {
			this.duration = duration;
		}
		public final int getTw() {
			return tw;
		}
		public final void setTw(int tw) {
			this.tw = tw;
		}
		public final int getTh() {
			return th;
		}
		public final void setTh(int th) {
			this.th = th;
		}
		public final String getImageId() {
			return imageId;
		}
		public final void setImageId(String imageId) {
			this.imageId = imageId;
		}
	}
}