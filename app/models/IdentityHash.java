package models;

import java.util.Random;

import javax.persistence.Entity;

import org.apache.commons.codec.digest.DigestUtils;

import play.db.ebean.Model;

@Entity
public class IdentityHash extends Model {

	private static final String saltPrefix = "deadsea";
	private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int saltMinLength = 10;
	private String salt;
	private String digest;

	// DONE add a dash of salt

	public IdentityHash(String password) {
		salt = saltPrefix + generateSalt();
		digest = DigestUtils.shaHex(password + salt);
	}

	public String getDigest() {
		return "sha256$" + digest;
	}

	public String getSalt() {
		return salt;
	}

	private static String generateSalt() {
		Random rand = new Random();
		int length = rand.nextInt(saltMinLength) + saltMinLength;

		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		return new String(text);
	}

}
