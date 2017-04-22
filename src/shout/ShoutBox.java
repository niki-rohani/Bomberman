package shout;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

import Tool.FontColor;

public class ShoutBox {
	
	 public static int BATTLE = 1;  public static int MENU = 0;
	
	
	ArrayList < ArrayList <String> >  m;
	ArrayList < ArrayList <String> > c;
	
	public ShoutBox () {
		m =  new ArrayList < ArrayList <String>  > ()  ;
		c =    new ArrayList <  ArrayList <String>     > () ;
		
		m.add (new ArrayList <String> () );
		m.add (new ArrayList <String> () );
		
		c.add (new ArrayList <String> () );
		c.add (new ArrayList <String> () );
	}
	
	public ArrayList <String> getMessage(int id) {
		return m.get(id);
	}
	
	public String getColor (int g, int id) {
		return c.get(id).get(g);
	}
	
	public void addMess (String arg, int id) {
		 m.get(id).add(arg.split("é")[0]);
		 String sp = arg.split("é")[1]; 
		  c.get(id).add(sp);
		 
		 
	}
	
	public static String appendHtml (String text, String font) {
		StringBuilder s = new StringBuilder();
		s.append("<div style=\"word-wrap: break-word; font-family: ").append(font).append("; \">");
         // not efficient but simple
         for(int i=0,l=text.length() ; i<l ; i++) {
             char ch = text.charAt(i);
             switch(ch) {
                 case '<': s.append("&lt;"); break;
                 case '>': s.append("&gt;"); break;
                 case '&': s.append("&amp;"); break;
                 case '"': s.append("&quot;"); break;
                 case ':':
                     if(text.startsWith(":)", i)) {
                         s.append("<img src=\"smiley\" alt=\":)\"/>");
                         i += 1; // skip one less because of i++ in the for loop
                         break;
                     }
                     s.append(ch);
                     break;
                 case 'h':
                     if(text.startsWith("http://", i)) {
                  int end = i + 7;
                         while(end < l && isURLChar(text.charAt(end))) {
                             end++;
                         }
                         String href = text.substring(i, end);
                         s.append("<a style=\"font: link\" href=\"").append(href)
                                 .append("\" >").append(href)
                                 .append("</a>");
                         i = end - 1; // skip one less because of i++ in the for loop
                         break;
                     }  
                 case 'w':
                     if(text.startsWith("www.", i)) {
                  int end = i + 4;
                         while(end < l && isURLChar(text.charAt(end))) {
                             end++;
                         }
                         String href = text.substring(i, end);
                         s.append("<a style=\"font: link\" href=\"").append(href)
                                 .append("\" >").append(href)
                                 .append("</a>");
                         i = end - 1; // skip one less because of i++ in the for loop
                         break;
                     } 
                     // fall through:
                 default:
                     s.append(ch);
             }
         }
         s.append("</div>");

         
         
         return s.toString();
     }


     private static boolean isURLChar(char ch) {
         return (ch == '.') || (ch == '/') || (ch == '%') ||
                 (ch >= '0' && ch <= '9') ||
                 (ch >= 'a' && ch <= 'z') ||
                 (ch >= 'A' && ch <= 'Z');
     
	}

	
	
}
