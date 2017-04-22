package com.bomberserver.GenericFonction;

public class FontColor {
	public static String [] color = {
		"yellow",
		"red",
		"blue",
		"green",
		"gray",
		"pink",
		"cyan",
		
	};
	
	public static String COLORSHOUTDEFAULT = "yellow";
	
	public static String getRandom() {
		return color [ (int) ( Math.random() * 5 ) ];
		
	}
	
	
	
}
