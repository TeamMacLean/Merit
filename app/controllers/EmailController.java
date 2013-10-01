package controllers;

import models.BadgeAssertion;
import models.User;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.EmailException;

import play.Configuration;
import play.Logger;
import play.Play;
import play.mvc.Http.Request;
import play.mvc.Http.*;
import views.html.*;

public class EmailController {

	private static Configuration config = Play.application().configuration();
	private static String hostname = config.getString("smtp.host");
	private static int port = config.getInt("smtp.port");
	private static Boolean ssl = config.getBoolean("smtp.ssl");
	private static Boolean tls = config.getBoolean("smtp.tls");
	private static String username = config.getString("smtp.user");
	private static String password = config.getString("smtp.password");
	private static String fromAddress = config.getString("smtp.from");
	private static Boolean usePasswords = config
			.getBoolean("smtp.usepasswords");

	public static void sendMail(String toAddress, String subject, String msg)
			throws EmailException {

		Logger.info("Preparing Email");

		HtmlEmail email = new HtmlEmail();
		email.setHtmlMsg(msg);
		// Email email = new SimpleEmail();
		email.setHostName(hostname);
		email.setSmtpPort(port);

		if (usePasswords) {
			email.setAuthenticator(new DefaultAuthenticator(username, password));
		}
		email.setSSLOnConnect(ssl);
		// email.setTLS(tls);
		email.setStartTLSEnabled(tls);
		email.setFrom(fromAddress);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(toAddress);

		Logger.info("Sending Email");

		email.send();
		Logger.info("EMAIL_SENT: " + fromAddress + " " + toAddress + " "
				+ subject + " " + msg);
		Logger.info("Sent email from: " + fromAddress + " To: " + toAddress
				+ " Subject: " + subject + " Message: " + msg);
	}

	public static boolean NotifyNewUser(User newUser, Request request) {

		String subject = "Welcome to MERIT";

		String url = routes.Application.index().absoluteURL(request, APIController.overSSL);

		String msg = emailTemplate(newUser.name, newUser.email, url,
				newUser.password);

		try {
			sendMail(newUser.email, subject, msg);
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			Logger.info("Failed! Sending Email");
			return false;
		}

	}

	
	public static boolean NotifyNewBadge(String recipient, BadgeAssertion ba, Request request){
		String subject = "Congratulations! You have earned a new badge";
		
		String link = routes.PublicController.giveBadge(ba.uid).absoluteURL(request, APIController.overSSL);
		
		String msg = newBadgeEmailTemplate("The Sainsbury Lab", link); //MAKE CHANGABLE

		try {
			sendMail(recipient, subject, msg);
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			Logger.info("Failed! Sending Email");
			return false;
		}
	}
	
	public static String newBadgeEmailTemplate(String issuer, String link){
		
		return newBadgeEmailTemplate(issuer, link);
	}
	
	private static String emailTemplate(String name, String username,
			String url, String password) {
		// @(name: String, username: String, url: String, password: String)
		return newUserEmailTemplate.render(name, username, url, password)
				.toString();
	}
}
