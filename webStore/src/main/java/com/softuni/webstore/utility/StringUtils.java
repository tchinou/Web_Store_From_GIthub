package com.softuni.webstore.utility;

public class StringUtils {
	public static String capEachWord(String source){
		if (source == null) return null;
	    String result = "";
	    String[] splitString = source.split(",");
	    for(String target : splitString){
	        result += Character.toUpperCase(target.charAt(0))
	                + target.substring(1) + " ";
	    }
	    return result.trim();
	}
}
