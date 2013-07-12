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

		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();

	}

}
