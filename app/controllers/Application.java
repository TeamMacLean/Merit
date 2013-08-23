package controllers;

import models.User;
import play.data.Form;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public final static String GLOBAL_FLASH_ERROR = "FLASH_ERROR";
	public final static String GLOBAL_FLASH_INFO = "FLASH_INFO";
	public final static String GLOBAL_FLASH_SUCCESS = "FLASH_SUCCESS";
	public final static String GLOBAL_FLASH_ALERT = "FLASH_ALERT";

	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}

	}

	/**
	 * Login page.
	 */
	public static Result login() {
		Form<Login> loginForm = new Form<Login>(Login.class);
		return ok(login.render(loginForm));
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {

		Form<Login> loginForm = new Form<Login>(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			Logger.info("A user failed to log in.");
			return badRequest(login.render(loginForm));
		} else {

			String emailAddress = loginForm.get().email;

			User user = User.findByEmail(emailAddress);

			if(user==null){
			flash(GLOBAL_FLASH_ERROR, "Sorry, We could not log you in. Please check your email address and password.");
			return badRequest(login.render(loginForm));
			}

			Logger.info("The user "+emailAddress+" has signed in.");

			session("email", user.email);
			return redirect(routes.Application.index());
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash(GLOBAL_FLASH_SUCCESS, "You've been logged out");
		return redirect(routes.Application.login());
	}

	public static Result index() {
		return redirect(routes.AdminController.index());
	}

}
