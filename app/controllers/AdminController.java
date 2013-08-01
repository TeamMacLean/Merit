package controllers;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import models.User;
import play.Logger;
import play.mvc.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class AdminController extends Controller {

	public static Result index() {
		// flash(Application.GLOBAL_FLASH_SUCCESS,
		// "You are currently on the Admin page");
		return ok(admin.render());
	}

	public static Result user(String email) {
		User currentUser = User.findByEmail(email);

		return ok(user.render(currentUser));

	}



}
