package controllers;

import java.util.List;

import models.AlignmentObject;
import models.BadgeAssertion;
import models.BadgeClass;
import models.Image;
import models.IssuerOrganization;
import models.Revocation;
import play.Logger;
import play.Play;
import play.data.Form;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.mvc.Controller;
import views.html.*;
import play.mvc.Result;

public class PublicController extends Controller {

	public static class EasyAssertion {

		public String Authorization;
		public String recipient;
		public String evidence;
		public Long badgeId;

	}

	// @BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getRecocationsAsJson() {
		List<Revocation> list = Revocation.find.all();
		return ok(Json.toJson(list));
	}

	public static String getAssetUrl(Long id) {
		return routes.PublicController.getAsset(id).absoluteURL(request());

	}

	public static Result EasyAssertion() {
		Form<EasyAssertion> assertionForm = new Form<EasyAssertion>(
				EasyAssertion.class);

		List<BadgeClass> badges = BadgeClass.find.all();
		
		return ok(views.html.easyassertion.render(assertionForm, badges));
//		return TODO;
	}

	public static Result addAssertion() {

		Form<EasyAssertion> assertionForm = new Form<EasyAssertion>(
				EasyAssertion.class).bindFromRequest();
		if (assertionForm.hasErrors()) {
			return badRequest("ERROR");
		}

		// form ok

		EasyAssertion ea = assertionForm.get();

		// I wish my wife was this dirty!
		Promise<WS.Response> result = WS.url(
				routes.APIController.createBadgeAssertion().absoluteURL(
						request())).post(
				"Authorization" + ea.Authorization + "&" + "recipient"
						+ ea.recipient + "&" + "evidence" + ea.evidence + "&"
						+ "badgeId=" + ea.badgeId);

		Logger.info("New Assertion response: " + result.get().getStatus());

		return TODO;
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
