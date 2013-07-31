package controllers;

import org.apache.commons.codec.binary.Base64;

import models.BadgeAssertion;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class APIController extends Controller {

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

			if(recipient==null){
				return badRequest("recipient==null");
			}
			if(evidence==null){
				return badRequest("evidence==null");
			}
			if(badgeID==null){
				return badRequest("badgeID==null");
			}
			
			BadgeAssertion ba = AssertionController.createBadgeAssertionAPI(recipient, badgeID,
					evidence);
			
			String assertionURL = routes.AssertionController.getAssertion(ba.uid).absoluteURL(request());
			
			//TODO return badgeAssertion URL to user

			return ok(assertionURL);
		}

		return TODO;
	}

}
