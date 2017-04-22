package com.net;







/**
 * Packet 
 * 				All the packet that can be send to the serv
 * 
 * 
 * INFO : The packet for battles retreive info of the battle.
 * Battle are serialize by the battle serial, the client serial, and the board serial.
 *	
 * @author Dantidot
 *
 */


public class Packet {

	
	
	
		public static class Packet205RunBattle {
			public int p;
			public int b;
		}
	
		public static class Packet220Direction {
			public int player;
			public int direction;
			public int battle;
	  
	}

		public static class Packet204PlayerExplode {

			public int id;

	}

		public static class Packet203PlayerInfo {
			public double x;
			public double y;
			public int player;
			public int battle;
			public int direction;
	}
		
		public static class Packet206PoseBomb {
			public double x;
			public double y;
			public int player;
			public int battle;
			public int bomb;
			public int playerCharacter;
			public int tileX;
			public int tileY;
		}
		
		public static class Packet208Transform {
			public int player;
			public int battle;
		}
		
		
		

		public static class Packet202BattleExplode {
			public int ev;
			public int x;
			public int y;
			public int rangeup;
			public int range ;
			public int rangerigth;
			public int rangeleft;

	}
		
		

		public static class Packet204BattleInfo {
			public String battle;
			public String client;
			public String board;

	}
		
		//////////////////// LOGIN PACKET /////////////////////
		// Request a login
		public static class Packet0loginRequest {
		public String ip;}
		// Login is accept
		public static class Packet1loginAccepted {public boolean b; public int id;   public int p;}
		
		
		//////////////// MESSAGE //////////////////////////////
		// Message
		public static class Packet2Message {public String message; public int battle; }
		
		// State is receive
		public static class Packet5StateReceived {boolean b;}
		
		
		/////////////// INFO ///////////////////////////////////////
		// Ask client connected
		public static class Packet30AskConected {public boolean b;}
		
		// Client that are connect to server
		public static class Packet20Conected {public String conected;}
		
		////////////////////////// BATTLE ///////////////////////////////
		// Ask to create
		public static class Packet22NewBattle {public String run; public String board; public int client;}
		
	
		// Battle that are waiting to be run
		public static class Packet21BattleWait { public String battle; public String client; }
		
		// Ask battle that are waiting
		public static class Packet23AskBattleWait {public boolean b;}
		
		// Ask join a battle
		public static class Packet24Joinn{public int b; public int client;}
		
		// Say that the battle is created
		public static class Packet25BattleCreateAccept{ public int b; public boolean accept; }
		
		// Feed if the client has join or not the battle
		public static class Packet200JoinBack {  public int b; public boolean accept; public String battle; public String client; public String board; public double playerNumber; public String boardBlock;   public String boardDec; public String boardBack; }
		
		// Ask info of battle
		public static class Packet26AskBattleInfo{  public int b;}
		
		
		public static class Packet27BattleInfo{public String battle; public String client;}
		public static class Packet6test{}
		public static class Packet7test{}
		public static class Packet8test{}
		
		
		public static class Packet28BattleSelect { public int player; public int battle; public int client; }
		public static class Packet29BattleGetSelect { public int battle; }
		public static class Packet201BattleSendS { public int battle; public int select; public boolean selection; }
		public static class Packet206PlayerDirection { public int direction;
		public int battle;  public int player; }
		public static class Packet202NewPlayer {
			public String client;
			
		}
		public static class Packet206DelPlay {
			public int play;
			public String client;
		}
		
		public static class Packet29BattleStart {
			
		}
		
		public static class Packet29Ready {
			public int client;
			public int b;
		}
		
		public static class Packet29StartingBattleIn {
			public int i;
		}
		public static class Packet29BattlePlay {
			
		}
		
		public static class Packet30BattleDecExplose {
			public int x;
			public int y;
		}
		public static class Packet29ReadyToFigth {
			public int client;
			public int b;
		}
		
		
		
		public static class Packet30BattlePlayDie {
			public int p;
		}
		
		
		public static class Packet200BattleQuit {
			public int player;
			public int id;
		}
		
		
		
		public static class Packet30BattleAddBonus {
			public int x;
			public int y;
			public int b;
		}
		
		public static class Packet30BattleDelBonus {
			public int x;
			public int y;
		}
		
		
	
}
