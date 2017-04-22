package com.bomberserver.listn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextArea;


import com.bomberserver.main.BomberServer;
import com.esotericsoftware.kryonet.Connection;
import com.net.Packet;

public class inListener implements KeyListener {
	public inListener (JTextArea in, BomberServer serv) {
		inServ = in; bomberServ = serv;
	}
	private JTextArea inServ; private BomberServer bomberServ;
	
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	    
		if (e.getKeyCode()==e.VK_ENTER) {
			String s = inServ.getText();
			e.consume();
			if (s.equals("")) {
				inServ.setText(null);
				
			}
			else {
			bomberServ.logger.info(" {server} " + s);
			inServ.setText("");
			}
			if (s.equals("server.close"))
				bomberServ.shut();
			else if (s.equals("players.write"))
				bomberServ.writePlayer();
			else if (s.equals("server.res")) {
				bomberServ.res();
				
			}
			else {
				String  [] sp = s.split(" ");
				if (sp[0].equals ("player.kick")) {
					boolean reussite = false;
						
							reussite = true;
						
						
					if (!reussite)
						bomberServ.logger.warning("The Player is not connect ");
					 	}
				
				else if (sp[0].equals ( "server.banip"   ) ) {
					if ( bomberServ.ban(sp[1])) 
					bomberServ.logger.info("Ip " + sp [1] + " is ban ");
						
				}
				
				else if (sp[0].equals   ( "server.ban" ) ) {
					Connection[] co = bomberServ.server.getConnections();
					
				}
				
				else if (sp[0].equals   ("server.unban") ) {
					bomberServ.unban(sp[1]) ;
					
				}
				
				else if (sp[0].equals   ("server.banlist") ) {
					bomberServ.logger.info(bomberServ.banlist());
				}
				
				else if (sp[0].equals   ("server.getbattlelist")) {
					
				}
				
				else if (sp[0].equals("server.sendAll")) {
					if (sp.length > 1) {
						String t = "";
						for (int i = 0; i < sp.length - 1; i++) {
							t = t + sp[i+1]  + " " ;
						}
					
					Packet.Packet2Message p = new Packet.Packet2Message();
					p.message = "[SERVEUR] " + t + "éred";
					bomberServ.getClientManager().sendToAllExceptTCP("", p);
					BomberServer.logger.info("[MESSAGE TO CLIENT] " + sp[1]);
					}
				}
				
						else
						 bomberServ.logger.warning(s + " is not a valid request" );
			}
			
		}
			
	}
	
}
