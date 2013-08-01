package controllers;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.EmailException;

import models.BadgeAssertion;
import models.User;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;

public class APIController extends Controller {

	private static final String ourAddress = "wookoouk@gmail.com";

	public static boolean checkAuth(String apiKey) {

		for (User u : User.find.all()) {
			String authString = u.email + ":" + u.password;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = "Basic " + new String(authEncBytes);
			if (authStringEnc.equals(apiKey)) {
				return true;
			}
		}
		return false;
	}

	public static Result createBadgeAssertion() {
		String authHead = request().getHeader("Authorization");

		if (authHead != null && checkAuth(authHead)) {
			String recipient = request().getHeader("recipient");
			String evidence = request().getHeader("evidence");
			String badgeID = request().getHeader("badgeId");

			if (recipient == null) {
				return badRequest("recipient==null");
			}
			if (evidence == null) {
				return badRequest("evidence==null");
			}
			if (badgeID == null) {
				return badRequest("badgeID==null");
			}

			BadgeAssertion ba = AssertionController.createBadgeAssertionAPI(
					recipient, badgeID, evidence);

			String assertionURL = routes.AssertionController.getAssertion(
					ba.uid).absoluteURL(request());

			// TODO return badgeAssertion URL to user

			// TODO Create and send email to user
			ThreadSendEmail emailRunner = new ThreadSendEmail(recipient,
					evidence, ba, request());
			new Thread(emailRunner).start();

			return ok(assertionURL);
		}

		return TODO;
	}

	private static class ThreadSendEmail implements Runnable {
		String recipient;
		String evidence;
		BadgeAssertion ba;
		Request request;

		public ThreadSendEmail(String recipient, String evidence,
				BadgeAssertion ba, Request request) {
			// TODO Auto-generated constructor stub
			this.recipient = recipient;
			this.evidence = evidence;
			this.ba = ba;
			this.request = request;
		}

		@Override
		public void run() {
			try {
				String url = routes.AssertionController.giveBadge(ba.uid)
						.absoluteURL(request);
				EmailController.sendMail(ourAddress, recipient,
						"You earnt a badge!",
						"You have received a badge to show your work on "
								+ evidence + ". " + url);
				;
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}
}
