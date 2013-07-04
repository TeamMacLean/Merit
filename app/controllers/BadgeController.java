package controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.joda.time.DateTime;

import models.*;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class BadgeController extends Controller {

	public static Result test(){
		return ok(test.render());
	}
	
	public static Result createBadgeAssertion() {

		IdentityHash ih = new IdentityHash("user-email-address");

		boolean hashed = true;

		IdentityObject io = new IdentityObject(ih, IdentityType.email, hashed,
				ih.getSalt());

		//TODO make badge here
		
		URL badgeURL = null;
		try {
			badgeURL = new URL("TODO");
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}

		URL verificationURL = null;
		try {
			verificationURL = new URL("TODO");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		VerificationObject vo = new VerificationObject(VerificationType.hosted,
				verificationURL);

		DateTime issuedOn = new DateTime();
		URL image = null;
		try {
			image = new URL("http://127.0.0.1:9000/assets/images/badge.png");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		URL evidence = null;
		try {
			evidence = new URL("TODO");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		DateTime expires = new DateTime();

		BadgeAssertion newBadgeAssertion = new BadgeAssertion(io, badgeURL, vo,
				issuedOn, image, evidence, expires);

		return ok(Json.toJson(newBadgeAssertion));

	}

//	public static Result getBadge(Long user, Long badge) {
//
//		Logger.info("Getting badge for user(id): " + user + ", badge(id):"
//				+ badge);
//
//		return TODO;
//	}

}
