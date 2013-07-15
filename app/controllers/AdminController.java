package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class AdminController extends Controller {

	public static Result index() {
		return ok(admin.render());
	}
}
