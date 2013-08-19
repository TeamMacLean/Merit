package controllers;

import java.util.List;

import models.User;
import play.data.Form;
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

	public static Result addUser() {
		Form<User> userForm = new Form<User>(User.class).bindFromRequest();
		if (userForm.hasErrors()) {
			List<User> users = User.findAll();
			return badRequest(useradmin.render(users, userForm));
			// return TODO;
		}
		User newUser = new User(userForm.get().name, userForm.get().email);
		newUser.save();
		EmailController.NotifyNewUser(newUser);

		return redirect(routes.AdminController.userAdmin());
	}

	public static Result deleteUser(Long id) {
		return TODO;
	}

	public static Result user(String email) {
		User currentUser = User.findByEmail(email);

		return ok(user.render(currentUser));

	}

}
