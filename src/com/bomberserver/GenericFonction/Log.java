package com.bomberserver.GenericFonction;

import com.bomberserver.main.BomberServer;
import com.net.Packet.Packet0loginRequest;

public class Log {
	
	public static String PLAYER_DISPLAY = "Player : "; 
	public static String CONNECTION = "[SERVER] " + " Someone is connecting  ID : ";
	public static String [] DISCO = { "[SERVER] "  + " Payer ID " , " has disco " } ;
	
	public static String [] CONNECTIONETABLISH =  { "[SERVER] The client " , " is connected"   } ;
	
	public static String [] RETREIVEINFO = { "[SERVER] Welcome to BomberServ version " + BomberServer.VERSION + " \n            If you need help type server.help" + " \n            The server is started on port "};
	
	
	public static void printLoggerInfo (String s) {
		BomberServer.logger.info( s );
		 
	}
}
