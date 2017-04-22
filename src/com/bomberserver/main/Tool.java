package com.bomberserver.main;

import java.util.ArrayList;

import com.bomberserver.InterfaceClient.Clients;
import com.net.Packet;

public class Tool {

	
	public static Clients getPlayer (int battle, int player, ArrayList <BattleTh> c) {
		  for (BattleTh t :  c ) {
			   if (t.getBattleID() == battle )
				   for (Clients client :  t.battleInfo.players) 
					   if (client.getID() == player  )
						   return client;
		   }
		  return null;
	}
}
