package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

@Entity
public class IdentityHash {

	private byte[] digest;

	public IdentityHash(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		digest = md.digest();
	}

	public String value() {
		return "sha256$" + digest;
	}

}
