package controllers;

import java.util.List;

import models.User;
import play.Logger;
import play.Play;
import play.data.Form;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class AdminController extends Controller {

	public static Result index() {
		// flash(Application.GLOBAL_FLASH_SUCCESS,
		// "You are currently on the Admin page");
		return ok(admin.render());
	}

	public static Result userAdmin() {

		Form<User> userForm = new Form<User>(User.class);
		List<User> users = User.findAll();
		return ok(useradmin.render(users, userForm));
		// return TODO;
	}

	public static String getUserName(String email){
		User user = User.findByEmail(email);
		if(user!=null){
			return user.name;
		} else {
			return "Signed In";
		}
	}
	
	public static Result addUser() {
		Form<User> userForm = new Form<User>(User.class).bindFromRequest();
		if (userForm.hasErrors()) {
			List<User> users = User.findAll();
			flash(Application.GLOBAL_FLASH_ERROR, "An unknown error occured.");
			return badRequest(useradmin.render(users, userForm));
			// return TODO;
		}
		User existingUser = User.findByEmail(userForm.get().email);
		if (existingUser != null) {
			flash(Application.GLOBAL_FLASH_ERROR,
					"A user with the email address \"userForm.get().email\" already exists.");
			List<User> users = User.findAll();
			return badRequest(useradmin.render(users, userForm));
		}

		User newUser = new User(userForm.get().name, userForm.get().email);
		newUser.save();
		if (!EmailController.NotifyNewUser(newUser, request())) {
			flash(Application.GLOBAL_FLASH_ERROR,
					"Cound not notify user, please check your email settings");
			newUser.delete();
		} else {
			flash(Application.GLOBAL_FLASH_SUCCESS,
					"User created and email sent");
		}

		return redirect(routes.AdminController.userAdmin());
	}

	public static Result deleteUser(Long id) {
		int userCount = User.findAll().size();
		if (userCount == 1) {
			flash(Application.GLOBAL_FLASH_ERROR,
					"You are the last user, You cant delete yourself");
			// return
			// badRequest("You are the last user, You cant delete yourself");
		} else {
			User user = User.findById(id);
			user.delete();
		}
		return redirect(routes.AdminController.userAdmin());
	}

	public static Result user(Long id) {
		User currentUser = User.findById(id);

		Form<Passwords> userForm = new Form<Passwords>(Passwords.class);

		return ok(user.render(currentUser, userForm));

	}

	public static Long getId(String email) {
		User u = User.findByEmail(email);
		if (u != null) {
			return u.id;
		} else {
			return null;
		}
	}

	public static Result updatePassword(Long id) {
		Form<Passwords> userForm = new Form<Passwords>(Passwords.class)
				.bindFromRequest();

		Form<Passwords> userFormClear = new Form<Passwords>(Passwords.class);

		User currentUser = User.findById(id);

		if (userForm.hasErrors()) {
//			flash(Application.GLOBAL_FLASH_ERROR,
//					"There was an error, please check your input");
			return badRequest(user.render(currentUser, userForm));
		}

		User userObj = User.findById(id);

		String oldPassword = userForm.get().oldPassword;
		String newPassword = userForm.get().newPassword;
		String newPasswordRepeat = userForm.get().newPasswordRepeat;

		if (oldPassword.equals(userObj.password)) {

			if (newPassword.equals(newPasswordRepeat)) {

				userObj.password = newPassword;
				userObj.save();

			} else {

				flash(Application.GLOBAL_FLASH_ERROR,
						"Your new passwords do not match");
				return badRequest(user.render(currentUser, userForm));
			}

		} else {
			flash(Application.GLOBAL_FLASH_ERROR,
					"Your current password does not match the one we have on file");
			return badRequest(user.render(currentUser, userForm));
		}

		flash(Application.GLOBAL_FLASH_SUCCESS, "Password changed");
		return ok(user.render(userObj, userFormClear));
	}

	public static class Passwords {
		@Required
		public String oldPassword;
		@Required
		public String newPassword, newPasswordRepeat;
		// @Required
		// public String newPassword;
		// @Required
		// public String newPasswordRepeat;
	}

}
