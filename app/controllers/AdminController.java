package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class AdminController extends Controller {

	public static Result index() {
		flash(Application.GLOBAL_FLASH_SUCCESS, "You are currently on the Admin page");
		return ok(admin.render());
	}
}
