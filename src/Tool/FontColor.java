package Tool;

import org.newdawn.slick.Color;



public class FontColor {
	public static String [] color = {
		"yellow",
		"red",
		"blue",
		"green",
		"gray",
		"white",
	};
	
	public static String COLORSHOUTDEFAULT = "yellow";
	
	public static String getRandom() {
		return color [ (int) ( Math.random() * 5 ) ];
	}
	
	
	
	public static Color color (String s) {
		if (check (s)) 
			if (s.equals("yellow"))
				return Color.yellow;
			else if (s.equals("red"))
				return Color.red;
			else if (s.equals("blue"))
				return Color.blue;
			else if (s.equals("green"))
				return Color.green;
			else if (s.equals("gray"))
				return Color.gray;
			else 
				return Color.white;
		else
			return Color.yellow;
	}
	
	private static boolean check (String s) {
	      int i = 0;
		while (i < color.length) {
			if (s.equals(color[i]))
				return true;
		i++;
		}
		return false;
	}
	
	
	
}

