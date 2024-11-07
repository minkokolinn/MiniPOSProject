package util;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordEncrypter {
	public static String encrypt(String password) {	//real password win lar pee /encrypted password return pyan
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedByte = digest.digest(password.getBytes());
			return Base64.getEncoder().encodeToString(encodedByte);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
