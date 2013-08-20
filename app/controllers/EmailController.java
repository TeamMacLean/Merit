package controllers;

import models.User;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.Configuration;
import play.Logger;
import play.Play;
import play.mvc.Http.*;

public class EmailController {

    private static Configuration config = Play.application().configuration();
    private static String hostname = config.getString("smtp.host");
    private static int port = config.getInt("smtp.port");
    private static Boolean ssl = config.getBoolean("smtp.ssl");
    private static Boolean tls = config.getBoolean("smtp.tls");
    private static String username = config.getString("smtp.user");
    private static String password = config.getString("smtp.password");
    private static String fromAddress = config.getString("smtp.from");


	public static void sendMail(String toAddress,
			String subject, String msg) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(hostname);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setSSLOnConnect(ssl);
//      email.setTLS(tls);
		email.setStartTLSEnabled(tls);
		email.setFrom(fromAddress);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(toAddress);
		email.send();
		Logger.info("EMAIL_SENT: " + fromAddress + " " + toAddress + " "
				+ subject + " " + msg);
        Logger.info("Sent email from: "+fromAddress+" To: "+toAddress+" Subject: "+subject+" Message: "+msg);
	}

	public static boolean NotifyNewUser(User newUser, Request request) {

		String subject = "Welcome to MERIT";
		String msg = "Welcome to "+routes.Application.index().absoluteURL(request)+".<br><br>Your username is "
				+ newUser.email
				+ " and your password is "
				+ newUser.password
				+ ".<br><br>You may change your password once you log in for the first time.";

		try {
			sendMail(newUser.email, subject, msg);
            return true;
		} catch (EmailException e) {
            e.printStackTrace();
            return false;
		}

	}
}
