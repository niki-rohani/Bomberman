package com.bomberserver.main;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

public class HopeHandler extends Handler {

	
	private JTextArea log;
	
	public HopeHandler (JTextArea text) {
		log = text;
	}
	
	

	public void close () {}
	
	public void flush () {}
	
	public void publish (LogRecord e) {
		log.append(format (e));
		log.setCaretPosition(log.getDocument().getLength());
		
	}
	
	public String format (LogRecord e) {
		StringBuilder b = new StringBuilder ();
		Level l = e.getLevel();
		
		if (l == Level.FINEST) {
			b.append("[FINEST] ");
		}
		else if (l == Level.FINER)
		{
			b.append (" [FINER] ");
		}
		else if (l == Level.FINE)
			b.append (" [FINE] ");
		else if (l == Level.INFO)
			b.append (" [INFO] ");
		else if (l == Level.WARNING)
			b.append (" [ATTENTION] ");
		
		
		b.append (e.getMessage());
		b.append("\n");
		
		Throwable t = e.getThrown();
		
		if (t == null) {
			
		}
		return b.toString();
	}
}
