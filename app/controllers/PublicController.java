package controllers;

import java.util.List;

import models.AlignmentObject;
import models.BadgeAssertion;
import models.BadgeClass;
import models.Image;
import models.IssuerOrganization;
import models.Revocation;
import play.Play;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class PublicController extends Controller {

public static Result giveBadge(Long id) {
		BadgeAssertion assertion = BadgeAssertion.find.byId(id);
		return ok(addtobackpack.render(assertion));
	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getRecocationsAsJson() {
		List<Revocation> list = Revocation.find.all();
		return ok(Json.toJson(list));
	}

	public static String getAssetUrl(Long id) {
		return routes.PublicController.getAsset(id).absoluteURL(request());

	}

	public static Result getAsset(Long id) {

		Image i = Image.find.byId(id);

		if (i != null) {
			String path = i.url;
			return ok((Play.application().getFile("/public/images/" + path)));
		} else {
			return badRequest("Image not found");
		}

	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static String getRecocationsString() {
		List<Revocation> list = Revocation.find.all();
		return Json.toJson(list).toString();
	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getIssuerJson(Long id) {
		IssuerOrganization io = IssuerOrganization.find.byId(id);
		return ok(Json.toJson(io));
	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getBadgeJson(Long id) {
		BadgeClass jsonBadge = BadgeClass.find.byId(id);
		return ok(Json.toJson(jsonBadge));
	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAssertion(Long id) {
		BadgeAssertion ba = BadgeAssertion.find.byId(id);
		return ok(Json.toJson(ba));
	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAlignmentJson(Long id) {
		AlignmentObject ao = AlignmentObject.find.byId(id);
		return ok(Json.toJson(ao));
	}
}
