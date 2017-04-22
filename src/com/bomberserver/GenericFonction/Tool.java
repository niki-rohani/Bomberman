package com.bomberserver.GenericFonction;

import java.util.ArrayList;

import com.bomberserver.InterfaceClient.Clients;
import com.bomberserver.main.Battle;
import com.bomberserver.main.BattleTh;
import com.bomberserver.main.BomberServer;
import com.net.Packet.*;

public class Tool {

	
	
	public static boolean checkColor (String c) {
		String [] s = FontColor.color;
		for (int  i = 0; i < s.length;  i ++ )
		if (c.equals(s[i]))
			return true;
		return false;
	}

	public static int createBattle(BomberServer manager, Packet22NewBattle objet) {
		
		int client = objet.client;
		String board = objet.board;
		String  name = objet.run ;
		
	//	return 0;
		return manager.createBattle(client, 4, name, board  );
		
	}
	
	
	
	public static Clients getPlayer (int battle, int player, ArrayList <BattleTh> c) {
		  for (BattleTh t :  c ) {
			   if (t.getBattleID() == battle )
				   for (Clients client :  t.battleInfo.players) 
					   if (client.getID() == player  )
						   return client;
		   }
		  return null;
	}
	
	public static BattleTh getBattle (int battle, ArrayList <BattleTh> t) {
		for (BattleTh r : t)
			if (r.getBattleID() == battle)
				return r;
		return null;
	}
	
	
	
	
}
