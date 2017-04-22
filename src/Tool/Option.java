package Tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;








import Objet.Resolution;

import com.net.Packet.*;
import com.net.Packet;
import com.bombershinobi.userIn.UserIn;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class Option {

	
	private String ip;
	private int port;
	private String sav = "option.info";
	private Resolution res;
	private UserIn userInterface;
	
	public Option() {
		ip = IP;
		port = PORT;
		res = new Resolution (800, 600);
		
		userInterface = new UserIn();
		
		
		try {
			try {
				
			BufferedReader option = new BufferedReader (new FileReader (new File (sav)));
			ip = option.readLine();
			port =    Integer.parseInt   ( option.readLine()  ) ;
			String [] ress = ( option.readLine()   ) .split   (":");
			
			res = new Resolution ( Integer.parseInt ( ress[0]), Integer.parseInt ( ress[1]) );
			option.close();
			
		} catch (IOException e  ) {
			
		}
		}
			catch ( java.lang.NumberFormatException e) {
				
			}
		userInterface.loadMap();
	}
	
	public Option(String p, int o, Resolution r) {
		ip = p;
		port = o;
		res =  r;
		
		try {
			FileWriter w = new FileWriter (new File (sav));
			w.write(ip + "\n" + port + "\n");
			w.write (res.getWidth()+":"+res.getHeight()  );
			w.flush();
			w.close();
		} catch (IOException e) {
		}
		
		
		userInterface = new UserIn();
		userInterface.save();
		
		
	}
	
	public String getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	

	
	public UserIn getUserInterface () {
		return userInterface;
	}
	
	public int getW() {
		return res.getWidth()   ;
		
		
	}
	
	
	public int getH() {
		return res.getHeight()     ;
	}
	
	
	
	public int getGameWindowHeight() {
		return    (int) ( getH() - 50  );
	}
	
	public int getGameWindowWidth () {
		return (int) (getW() - 50 );
	}
	
	
	
	public int gameX() {
		return 30;
	}
	
	public int gameY() {
		return 10;
	}
	
	
	
	
	
	
	public static int PORT = 54555;
	public static String IP = "127.0.0.1";
	
	
}
