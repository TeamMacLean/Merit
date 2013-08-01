package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.persistence.Entity;

import org.apache.commons.codec.digest.DigestUtils;

import play.db.ebean.Model;

@Entity
public class IdentityHash extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 965039885280462355L;
	private String salt;
	private String digest;

	// DONE add a dash of salt

	public IdentityHash(String identity) {
		salt = generateSalt();

		String saltPlusPlainTextPassword = new String(identity)+salt;
		try {
			digest = hash256(saltPlusPlainTextPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("DIGEST: " + digest);
	}

	public String getDigest() {
		return "sha256$" + digest;
	}

	public String getSalt() {
		return salt;
	}

	public static String hash256(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		try {
			md.update(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bytesToHex(md.digest());
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte byt : bytes)
			result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(
					1));
		return result.toString();
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
