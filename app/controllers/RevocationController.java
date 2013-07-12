package controllers;

import java.util.List;


import models.Revocation;
import play.*;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.Request;
import views.html.*;

public class RevocationController extends Controller {

	public static Result addRevocation() {
		return TODO;
	}

	public static Result getRecocationsAsJson() {
		List<Revocation> list = Revocation.find.all();
		
		
		return ok(Json.toJson(list));
	}

	public static String getUrl(Request request) {
		String url = routes.RevocationController.getRecocationsAsJson().absoluteURL(request);
		return url;
	}

}
