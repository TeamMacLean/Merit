package controllers;

import models.User;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.Logger;

public class EmailController {

	public static void sendMail(String fromAddress, String toAddress,
			String subject, String msg) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
		email.setSSLOnConnect(true);
		email.setFrom(fromAddress);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(toAddress);
		email.send();
		Logger.info("EMAIL_SENT: " + fromAddress + " " + toAddress + " "
				+ subject + " " + msg);
	}

	public static void NotifyNewUser(User newUser) {
		// TODO Auto-generated method stub

		String fromAddress = "TODO";
		String subject = "Welcome to MERIT";
		String msg = "Your username is "
				+ newUser.email
				+ " and your password is "
				+ newUser.password
				+ ". You may change your password once you log in for the first time.";

		try {
			sendMail(fromAddress, newUser.email, subject, msg);
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}
}
