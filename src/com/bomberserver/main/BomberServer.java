package com.bomberserver.main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;







import com.bomberserver.GenericFonction.FontColor;
import com.bomberserver.GenericFonction.Log;
import com.bomberserver.InterfaceClient.ClientManager;
import com.bomberserver.InterfaceClient.Clients;
import com.bomberserver.listn.NetworkListener;
import com.bomberserver.listn.inListener;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.net.register;
import com.net.Packet.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;




/**
 * BomberServer : This class is the manager of the server.
 * The server open a new Server class and listen to the port define in properties.
 * When a new client connect to the main Server, the BomberServer open a new Server with the ip of the client
 * and a port that is available ( clientManager ). The new id of the client is the IP + the Port
 * the Server close the connection with the client and send the information of the new server.
 * The client will connect to the new server that is open in TCP and UDP with the port clientManager.getClient(id).getPort
 * 
 * The client manager manage all client, by a ID that is the ip and the port opened.
 * 
 * 
 * 
 * If a client is removed, the server with id of IP + p is close, and the client manager free the port for new
 * client.
 * 
 * For more information see ClientManag
 * 
 * ---------------------------------------------------
 * Battle are in battleRun (see battleTh)
 * 
 * 
 * @author Dantidot
 *
 */
@SuppressWarnings("serial")
public class BomberServer extends JPanel {
	
	
	
	
	
	private int battle;
	
	// There is the client manager
	public ClientManager clientManager;
	
	
	// The Network
	public Server server;
	public JList clientPanel;
	
	// The logger
	public static Logger logger = Logger.getLogger("Bomber");
	
	// List of message ( chat )
	public Message message;
	
	// Option
	private Propriete propriete;
	public Propriete getPropriete() {
		return propriete;
	}



	// Version Of the Serveur
	public static float VERSION = 0.2f;
	
	// List of battles that are running
	public ArrayList <BattleTh> battleRun;
	
	
	public int maxBattle;
	
	// Ban
	public static String banfile = "ban.txt";
	
	// List of clients id's
	// Clients 
	public HashMap <Integer, String> id;
	
	 
	public BomberServer() throws IOException {
		// Création des clients
		clientManager =   new ClientManager(this)  ;
		
		// On charge les proprietes
		propriete = new Propriete();
		
		// On afficher le tout
		logger.info("Démarage du serv \" " + propriete.getName() + " \" sur le port " + propriete.getPort());
		setPreferredSize(new Dimension(600,400));
		setLayout(new BorderLayout());
		clientPanel = getJListClient();
		add(clientPanel, "North");
		add(getLog(), "Center");
		maxBattle = 0;
		start(); battleRun = new ArrayList <BattleTh> ();
		logger.info(Log.RETREIVEINFO[0] + propriete.getPort()  );  
		
	
		id = new HashMap <Integer, String> ();
		
		battle = 0;
		
	  }
	  
	  
	
	/**
	 * start :
	 * 			Start the network manager
	 * @throws IOException
	 */
	public void start() throws IOException {
		server = new Server();
		register.register(server, 1);
		server.addListener(new NetworkListener(this, propriete.getPort(), "", server));
		server.bind(propriete.getPort(), propriete.getPort());
		server.start();
		
	}
	
	
	
	
	public static void main (String [] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BomberServer bs = null;
		try {
			bs = new BomberServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame ("Bomber serv");
		frame.add(bs);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	
	}
	
	
	public void  shut() {
		System.exit(0);
	}
	
	
	public void writePlayer() {
		Log.printLoggerInfo(Log.PLAYER_DISPLAY + clientManager);
	}
	
	
	/**
	 * getJListClient :
	 * 					Get the JList with the client
	 * @return	JList
	 */
	public JList getJListClient() {
		JList pane = clientManager.getJList();
		pane.setBorder(new EtchedBorder());
		pane.setPreferredSize(new Dimension(600, 200));
		
		return pane;
	}
	
	
	/**
	 * getLog :
	 * 			get the logger panel
	 * @return	JPanel
	 */
	public JPanel getLog() {
		JPanel getlog = new JPanel(new BorderLayout());
		JTextArea logText = new JTextArea();
		logger.addHandler(new HopeHandler(logText));
		JScrollPane logJ = new JScrollPane (logText);
		logText.setEditable(false);
		
		JTextArea in = new JTextArea ();
		in.addKeyListener(  new inListener(in, this));
		getlog.add(logJ, "Center");
		getlog.add(in, "South");
		
		
		getlog.setBorder(new EtchedBorder());
		return getlog;
	}
	
	
	
	/**
	 * createBattle 
	 * 				Create a battle, and put it in the array
	 * @param player : player id of the create
	 * @param joueur : max player
	 */
	public int createBattle (int player, int joueur, String boardPath, String bat) {
		System.out.println("Trying to create battle");
		battleRun.add(new BattleTh (Battle.createBattle(player, joueur, boardPath, battle, bat), this, battle) )  ;
		battle++;
		return battle - 1;
	}
	
	/**
	 * remove
	 * 			Remove a client
	 * @param i	client id
	 * @param c	the connexion of the client
	 */
	public void removeClient (String i, Connection c) {
		clientManager.removeClient(i);
		clientPanel.setListData( clientManager.getClientArray()) ;
		clientPanel.repaint();
		
		
		
		
	}public String message (Packet2Message r) {

		String [] message = r.message.split(" ");
		if (message[0].equals("createbattle")) {
				createBattle (Integer.parseInt (message[1]), Integer.parseInt (message[2]), message[3]);
				return "battlecreate"; }
				return null;
		}
	
	
	public boolean checkBan (String s) {
		
		File f = new File (banfile);
		BufferedReader fread;
		try {
			fread = new BufferedReader (new FileReader(f));
		
		String readlin;
		while (  ( readlin = fread.readLine()) != null)  {
			if (readlin.equals(s)) {
				fread.close();
				
				return true;
			}
		}
		} catch (IOException e) {
		}
		
		
		return false;
		
	}
	
	public String banlist () {
		String ban = "";
		File f = new File (banfile);
		BufferedReader fread;
		
			try {
				fread = new BufferedReader (new FileReader(f));
			
		String readlin = fread.readLine() ;
		
		while (   readlin != null)  {
				ban =  ban + readlin + "\n";	
			readlin = fread.readLine();
		}
		
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return ban;
	}
	
	
	public boolean ban (String s) {
		String ban = "";
		File f = new File (banfile);
		BufferedReader fread;
		try {
			fread = new BufferedReader (new FileReader(f));
			
		String readlin = fread.readLine() ;
		
		while (   readlin != null)  {
			if (readlin.equals(s) == false) {
				ban =  ban + readlin + "\n";	
			}
			else 
				return true;
			
			readlin = fread.readLine();
		}
		fread.close();
		PrintWriter w = new PrintWriter (new FileWriter (f));
		ban = ban + s;
		w.println(ban);
		w.flush();
		w.close();
		return true;
		} catch (IOException e) {
			return false;
		}
		
		
	}
	
	public boolean unban (String s) {
		String ban = "";
		File f = new File (banfile);
		BufferedReader fread;
		try {
			fread = new BufferedReader (new FileReader(f));
			
		String readlin;
		while (  ( readlin = fread.readLine()) != null)  {
			
			if (readlin.equals(s) == false) {
				ban =  ban + readlin  + "\n" ;
				
			}
			
		}
		
		fread.close();
		PrintWriter w = new PrintWriter (new FileWriter (f));
		
		w.println(ban);
		w.flush();
		
		
		return true;
		} catch (IOException e) {
			return false;
		}
		
		
		
	}
	
	
	
	/**
	 * add
	 * 		Add a new client to the array
	 * @param ID : 		The id of client
	 * @param color : The color of the client
	 * @param ip 
	 * @param serveur	: The network server of the client
	 * @param p : the port of the network
	 */
	public void addClient(int ID, String color, String ip, Server serveur, int p) {
		clientManager.addClient(ip, ID, serveur, p, color);
		id.put(ID, ip+p);
		

		  
		         clientPanel.setListData(clientManager.getClientArray());
		//   clientPanel = getClient();
		
		clientPanel.repaint();
		
		
	}
	
	
	
	
	
	
	public ClientManager getClientManager() {
		return clientManager;
	}




	public void setClientManager(ClientManager clientManager) {
		this.clientManager = clientManager;
	}




	public void runBattle(int num) {
		BattleTh b = null;
		for (int i=0; i < battleRun.size(); i ++    )
			if (battleRun.get(i).getBattleID() == num)
				b = battleRun.get(i);
		if (!b.isRun()) {
			System.out.println ("Start");
			b.start();
		}
			
	}
	
	public BattleTh[] battleListWait () {
		int j = 0;
		for (int i = 0; i < battleRun.size(); i++ ) {
			if (battleRun.get(i).isRun() != true )
				j ++;	
		}
		
		BattleTh [] b = new BattleTh [j];
		
		for (int i = 0; i < battleRun.size(); i++) {
			if (battleRun.get(i).isRun() != true)
				b[i] = battleRun.get(i);
		}
		
		return b;
	}




	public void res() {
		server.close();
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		battleRun.clear();
		
		
	
	}



	/**
	 * create
	 * 			Create a new Server, for a new client
	 * @param p	The port of the server
	 * @param ip	The ip of the client
	 * @return
	 */
	public Server createServer(int p, String ip) {
		Server s = new Server(2000000,2000000);
		register.register(s, 1);
		s.addListener(new NetworkListener(this, p, ip, s));
		try {
			s.bind (p , p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			s.start();
		// TODO Auto-generated method stub
		return s;
	}

	
	
	/**
	 * getStringID
	 * 				get the String id of a client with the int id, the string id is IP + P, the int id is the id
	 * 				of the client in the main network.
	 * @param i
	 * @return
	 */
	public String getStringIDbyIntID(int i) {
		return id.get(i);
	}
	
	public int addPlayerBattle (int playerID, int Battle, String player) {
		if (id.get(playerID).equals(player)  ) {
			if (getBattleId(Battle)   != null )
			return ( getBattleId (Battle).addPlayer(clientManager.getClient(player))  ) ;
			logger.info("Battle " + Battle + " is null " );
			return 0;
		}
		logger.info("The client " + playerID + " is not the client !!! THE CLIENT CANNOT ENTER "  + id.get(playerID) + " " + player);
		
		return 0;
	}
	
	
	public BattleTh getBattleId (int b) {
		for (int i = 0; i < battleRun.size(); i++    )
			if (battleRun.get(i).getBattleID() == b)
				return battleRun.get(i);
		return null;
	}
	
	public void endGame (BattleTh b) {
		battleRun.remove (b);
	}
	
	
	

}
