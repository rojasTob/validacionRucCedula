package com.validation.util;

public class ValidationUtil {
	
		
	public static boolean containsOnlyNumbers(String identification){
		String regex = "\\d+";
		return identification.matches(regex);
	}
	
	public static boolean hasDigitsLikeARuc(String identification){
		return (identification.length() == 13 && identification.substring(10,13).compareTo("001")==0);
	}
	
}
