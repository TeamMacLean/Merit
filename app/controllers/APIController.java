package controllers;

import org.apache.commons.codec.binary.Base64;

import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class APIController extends Controller {

	public String email;
	public String badgeURL;

	// TODO
	// check for valid apiKey
	// create badge assertion

	// {
	// "uid": "f2c20", //gen
	// "recipient": {
	// "type": "email", //api
	// "hashed": true, //always
	// "salt": "deadsea", //same
	// "identity":
	// "sha256$c7ef86405ba71b85acd8e2e95166c4b111448089f2e1599f42fe1bba46e865c5"
	// //gen
	// },
	// "image": "https://example.org/beths-robot-badge.png", //api? or from
	// badge type (just use one from badge?)
	// "evidence": "https://example.org/beths-robot-work.html", //api
	// "issuedOn": 1359217910, //gen
	// "badge": "https://example.org/robotics-badge.json", //api
	// "verify": {
	// "type": "hosted",
	// "url": "https://example.org/beths-robotics-badge.json" //this.url
	// }
	// }

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
			
			AssertionController.createBadgeAssertionAPI(recipient, badgeID,
					evidence);

			return ok("Accepted");
		}

		return TODO;
	}

}
