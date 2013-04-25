package fr.ecotilt.appui.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Singleton Utils for USERS
 * @author Philippe
 */
public class UserUtil {

	private UserUtil() {
	}
	
	private static class LazySingleton {
		static UserUtil	instance = new UserUtil();
	}

	public static UserUtil getInstance() {
		return LazySingleton.instance;
	}
	
	public StringBuffer hashPassword(String value) {
		try {
			byte[] bMessage = value.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(bMessage);
			
			byte[] bResult = messageDigest.digest();
			
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < bResult.length; i++) {
	         sb.append(Integer.toString((bResult[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb;
	        //String encryptedString = new String(bResult);
	        //return encryptedString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void main(String[] args) {
		System.out.println("*******************************************");
		StringBuffer hexString = UserUtil.getInstance().hashPassword("040504740dsq80c44dsq0c5ds4s");
		System.out.println("Hex format : " + hexString.toString());
	}
}
