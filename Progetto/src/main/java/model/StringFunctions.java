package model;

import java.nio.charset.StandardCharsets;

public interface StringFunctions {
	public default String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString( 
						(hash[i] & 0xFF) | 0x100 
						).toLowerCase().substring(1,3);
			}
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return hashString;
	}

	public default String filter(String s) {
		StringBuilder filtered=new StringBuilder(s.length());
		char c;

		for (int i=0; i<s.length(); i++) {
			c=s.charAt(i);

			switch (c) {
			case '<':
				filtered.append("&lt");
				break;
			case '>':
				filtered.append("&gt");
				break;
			case '"':
				filtered.append("&quot");
				break;
			case '&':
				filtered.append("&amp");
				break;
			default:
				filtered.append(c);
				break;
			}
		}
		return filtered.toString();
	}
}
