package com.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Client;
import com.net.Packet.*;

public class register {

	public static void register(Object server, int i) {
		Kryo kryo;
		if (i == 1)
			kryo = ((Server) server).getKryo();
		else
			kryo = ((Client) server).getKryo();
		kryo.register(Packet.Packet220Direction.class);
		kryo.register(Packet0loginRequest.class);
		kryo.register(Packet1loginAccepted.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet22NewBattle.class);
		kryo.register(Packet30AskConected.class);
		kryo.register(Packet.Packet20Conected.class);
		kryo.register(Packet.Packet23AskBattleWait.class);
		kryo.register(Packet.Packet21BattleWait.class);
		kryo.register(Packet.Packet24Joinn.class);
		kryo.register(Packet.Packet25BattleCreateAccept.class);
		kryo.register(Packet.Packet200JoinBack.class);
		

		kryo.register(Packet.Packet28BattleSelect.class);
		kryo.register(Packet.Packet29BattleGetSelect.class);
		kryo.register(Packet.Packet201BattleSendS.class);
		kryo.register(Packet.Packet202BattleExplode.class);

		kryo.register(Packet.Packet202NewPlayer.class);

		kryo.register(Packet.Packet205RunBattle.class);

		kryo.register(Packet.Packet203PlayerInfo.class);

		kryo.register(Packet.Packet206PoseBomb.class);

		kryo.register(Packet.Packet206DelPlay.class);
		
		kryo.register(Packet.Packet29BattleStart.class);

		kryo.register(Packet.Packet29Ready.class);
		kryo.register(Packet.Packet29BattlePlay.class);

		
		kryo.register   (Packet.Packet30BattleDecExplose.class);
		kryo.register(Packet.Packet29ReadyToFigth.class);

		
		
		
		kryo.register(Packet.Packet30BattlePlayDie.class);

		
		
		
		
		kryo.register (Packet.Packet30BattleAddBonus.class);
		
		
		
		
		
					
	}
}
