package controllers;

import java.util.List;

import models.AlignmentObject;
import models.BadgeAssertion;
import models.BadgeClass;
import models.IssuerOrganization;
import models.Revocation;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class PublicController extends Controller {

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getRecocationsAsJson() {
		List<Revocation> list = Revocation.find.all();
		return ok(Json.toJson(list));
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static String getRecocationsString() {
		List<Revocation> list = Revocation.find.all();
		return Json.toJson(list).toString();
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getIssuerJson(Long id) {
		IssuerOrganization io = IssuerOrganization.find.byId(id);
		return ok(Json.toJson(io));
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getBadgeJson(Long id) {
		BadgeClass jsonBadge = BadgeClass.find.byId(id);
		return ok(Json.toJson(jsonBadge));
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAssertion(Long id) {
		BadgeAssertion ba = BadgeAssertion.find.byId(id);
		return ok(Json.toJson(ba));
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAlignmentJson(Long id) {
		AlignmentObject ao = AlignmentObject.find.byId(id);
		return ok(Json.toJson(ao));
	}
}
