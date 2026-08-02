package com.res.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.res.exception.RESBusinessException;

public class SHAHashingUtil {
	
	static SecureRandom r = new SecureRandom();

	public static StringBuffer encryptPassword(String password) throws RESBusinessException {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			throw new RESBusinessException("Exception Occured.", e);
		}
	}

	public static String generatePassword() {
		return generateSessionKey(8);
	}

	private static String generateSessionKey(int length) {
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;
	}

}
