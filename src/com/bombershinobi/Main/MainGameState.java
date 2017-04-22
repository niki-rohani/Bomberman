package com.bombershinobi.Main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.*;
import particuleObjet.particule;
import rendu.UIAbstract;
import shout.ShoutBox;

import com.bombershinobi.Manager.EventManager;
import com.bombershinobi.Manager.MusicManager;
import com.bombershinobi.Manager.NetworkManager;
import com.bombershinobi.Manager.ParticleManager;
import com.bombershinobi.Manager.RessourceManager;

import State.TWLStateBasedGame;
import State.concret.MainMenu;
import State.concret.Menu;
import State.concret.MenuJeu;
import State.concret.goToOption;

import Tool.LoadingInfo;
import Tool.Option;
import Objet.Battle;
import Objet.ClientPlay;

import com.net.register;

public class MainGameState extends TWLStateBasedGame {

	// Etats de jeu
	public static int MAINMENU = 1;
	public static int DECONECTED = 2;
	public static int CONNEXION = 20;
	public static int MENU = 2;
	public static int INTRO = 7;
	public static int OPTION = 0;
	public static int GOTOOPTION = 8;
	public static int LOBBY = 31;
	public static int JEU = 41;
	// ////////////////////////////////////
	
	
	private MusicManager musicManag;
	public static MainGameState CONTROLLER;
	private AppGameContainer container; // le conteneur du jeu
	private NetworkManager networkManager;
	// Les options
	private Option option;
	// Dernier état visité
	private int lastState;
	// ShoutBox
	private ShoutBox shout;
	private int nextS;
	private ArrayList <Battle> battles;
	private HashMap <String, UIAbstract> UIObjet;
	private ParticleManager particle;
	// Les ressources chargées sont stockées ici
	private RessourceManager manager;
	private EventManager event;


	
	
	public EventManager getEventManager() {
		return event;
	}
	
	public ParticleManager getParticleManager() {
		return particle;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	
	public AppGameContainer getContainer() {
		return container;
	}

	public void setContainer(AppGameContainer container) {
		this.container = container;
	}


	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public int getLastState() {
		return lastState;
	}

	public void setLastState(int lastState) {
		this.lastState = lastState;
	}

	public ShoutBox getShout() {
		return shout;
	}

	public void setShout(ShoutBox shout) {
		this.shout = shout;
	}

	public RessourceManager getManager() {
		return manager;
	}

	public void setManager(RessourceManager manager) {
		this.manager = manager;
	}
	
	
	public MusicManager getMusicManager() {
		return musicManag;
	}


	

	public MainGameState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		// New Instance of this
		CONTROLLER = this;
		manager = new RessourceManager();	
		option = new Option();
		shout = new ShoutBox();
		
		battleWait = false;
		lastState = INTRO;
		setNextS(MENU);
		battles = new ArrayList <Battle> ();
		nextS = MENU;
		UIObjet = new HashMap <String, UIAbstract> ();
		networkManager = new NetworkManager();
		musicManag = new MusicManager();
		particle = new ParticleManager();
		event = new EventManager();
		
	}
	
	
	public UIAbstract getUIObjet (String s) {
		return UIObjet.get(s);
	}
	
	
	public void addUIObjet (String s, UIAbstract ui) {
		UIObjet.remove (s);
		UIObjet.put(s, ui);
	}
	
	public void clearUI () {
		UIObjet.clear();
	}
	
	
	


	/**
	 * initStatesList : Let you init the list of states Initialise la liste des
	 * états
	 */
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.container = (AppGameContainer) arg0;
		
		try {
			loadRessource(new FileInputStream ("Ressource/Descriptor/load.xml"),   false, new LoadingInfo() );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		

		/*  addState (new State.test(this));
		this.enterState(21312);  */
		addState(new State.concret.Introduct(this));
		this.enterState(INTRO);
		addState(new Menu(this));
		addState (new MenuJeu(this));
		
		addState(new State.concret.OptionSta(this));
		addState(new State.concret.Connexion(this));
		addState  (new goToOption(this));
		
		
		addState (new MainMenu(this));
		
		addState (new State.concret.Game(this));
		
	}

	

	
	

	public void newBattle(String battle, String board) {
		battleWait = false;
		System.out.println("SEND new battle request");
		networkManager.getServer().sendNewBattle(battle, board);
		
	}

	/**
	 * Ask if a battle is running for this client Demande si un battle est en
	 * cours pour le client
	 * 
	 * @return true if battle is running
	 */
	public boolean isBattleRuning() {
		return isBattleOn && (battle == null) == false  ;
	}
	
	public void battle() {
		isBattleOn = true;
	}

	/**
	 * Add a mess to the shout Ajoute un message dans la shoutBox
	 * 
	 * @param add
	 *            : Message to add
	 */
	public void addMessage(String add, int id) {
		shout.addMess(add, id);
	}

	
	/**
	 * Let you load Ressources Permet de charger des ressources
	 * 
	 * @param i
	 *            : The file
	 * @param t
	 *            : true if loading is
	 * @return true if the ressource is loaded
	 */
	public boolean loadRessource(InputStream i, boolean t, LoadingInfo inf) {
		try {
			manager.loadResources(i, t, inf);
		} catch (SlickException e) {
		}
		return true;
	}

	/**
	 * Return the ressource Manag Retourn le manag de Ressource
	 * 
	 * @return
	 */
	public RessourceManager getRessource() {
		return manager;
	}


	
	public boolean isPressed (String s) {
		  return option.getUserInterface().isPressed(s, this.getContainer().getInput()) ;
	}
	
	public boolean isDown (String s) {
		     return option.getUserInterface().isDown (s, this.getContainer().getInput() );
	}
	
	
	
	/**
	 * Exit the game Sort du jeu
	 */
	public void quit() {
		networkManager.getClient().close();
		container.exit();
	}

	/**
	 * Return if a battle is wait for create Retourne si un battle attends
	 * d'être construit
	 * 
	 * @return
	 */
	public boolean isBattleWait() {
		return battleWait;

	}

	public static void main(String[] args) {
		try {
			MainGameState s = new MainGameState(" ALPHA BOMBER ");
			AppGameContainer container = new AppGameContainer(s);
			container.setDisplayMode(s.option.getW(), s.option.getH(), false);
			container.setMinimumLogicUpdateInterval(20);
			container.setMaximumLogicUpdateInterval(30);
			container.setTargetFrameRate(60);
			container.setAlwaysRender(true);
			container.setUpdateOnlyWhenVisible(false);
			container.start();
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Battle getBattleWaiting() {
		return battleWaiting;
	}

	public void setBattleWaiting(Battle battleWaiting) {
		this.battleWaiting = battleWaiting;
	}
	

	public ArrayList<Battle> getBattles() {
		return battles;
	}

	public void setBattles(ArrayList<Battle> battles) {
		this.battles = battles;
	}

	

	public Battle battle;
	
	private Battle battleWaiting;

	public boolean battleWait;

	public boolean isBattleOn;
	
	public int battleNumber;

	public ClientPlay play;
	public String[] resolution;

	public void getListBattleWait() {
	     networkManager.getServer().getListBattle();
		
	}

	

	public int nextState() {
		// TODO Auto-generated method stub
		return nextS;
	}

	public void setNextS(int nextS) {
		this.nextS = nextS;
	}

	@Override
	protected URL getThemeURL() {
		
		// TODO Auto-generated method stub
		
		try {
			return   manager.getFile("FILE_XML_THEME").toURL() ;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
		
	
	
	
	public ClientPlay getPlay() {
		if (battle == null)
			return null;
		for (int i = 0; i < battle.players.length; i++)
			if ( (battle.players[i] == null ) == false &&   networkManager.getId() == battle.players[i].getID() )
				return battle.players[i];
		return null;
		
		
	}
	
	
	
	
	

	

	
	

}