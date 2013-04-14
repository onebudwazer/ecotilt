package fr.ecotilt.appui.util;

import java.util.regex.Pattern;

public class CodePostal {

	private static final String regex = "^\\d{5}";
	
	public static boolean isAValidZipCode(int zipValue) {
		String zip = String.valueOf(zipValue);
		return Pattern.matches(regex, zip);
	}
	
	public static void main(String[] args) {
		System.out.println(isAValidZipCode(99999));
	}

}
