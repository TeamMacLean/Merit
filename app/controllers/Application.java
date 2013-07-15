package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public final static String GLOBAL_FLASH_ERROR = "FLASH_ERROR";
	public final static String GLOBAL_FLASH_INFO = "FLASH_INFO";
	public final static String GLOBAL_FLASH_SUCCESS = "FLASH_SUCCESS";
	public final static String GLOBAL_FLASH_ALERT = "FLASH_ALERT";

	public static Result index() {
		return redirect(routes.AdminController.index());
	}

	public static Result test() {
		return ok(test.render());
	}

}
