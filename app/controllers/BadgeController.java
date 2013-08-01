package controllers;

import java.util.Arrays;
import java.util.List;

import models.*;
import models.Image.imageType;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class BadgeController extends Controller {

	public static Result badges() {

		// list badge types
		// add new badge types

		Form<BadgeClass> badgeForm = new Form<BadgeClass>(BadgeClass.class);

		List<BadgeClass> badgesList = BadgeClass.find.all();

		// need to pass list of images, criteria, issuers, alignments.
		// tags can be a text box (split by ,)

		List<Image> images = Image.find.where()
				.eq("imageType", imageType.badge).findList();

		List<IssuerOrganization> issuers = IssuerOrganization.find.all();

		List<AlignmentObject> aos = AlignmentObject.find.all();

		return ok(badges.render(badgesList, images, issuers, badgeForm, aos));
	}

	public static Result addBadge() {

		Form<BadgeClass> badgeForm = new Form<BadgeClass>(BadgeClass.class)
				.bindFromRequest();

		if (badgeForm.hasErrors()) {
			List<BadgeClass> badgesList = BadgeClass.find.all();

			List<Image> images = Image.find.where()
					.eq("imageType", imageType.badge).findList();

			List<IssuerOrganization> issuers = IssuerOrganization.find.all();
			List<AlignmentObject> aos = AlignmentObject.find.all();
			return badRequest(badges.render(badgesList, images, issuers,
					badgeForm, aos));
		}

		String tagsOneLine = badgeForm.get().tagsOneLine;

		List<String> tags = Arrays.asList(tagsOneLine.split("\\s*,\\s*"));

		Logger.info("TAGS, INPUT= " + tagsOneLine);

		// Logger.info("TAGS, List=");

		String issuerIdString = badgeForm.get().issuerString;

		Long issuerId = Long.parseLong(issuerIdString);

		if (issuerId == null) {
			Logger.error("issuerId is EFFED up!");
		}

		String issuerURL = routes.PublicController.getBadgeJson(issuerId)
				.absoluteURL(request());

		// AlignmentObject ao =
		// AlignmentObject.find.byId(Long.parseLong(badgeForm.get().alignmentString));

		BadgeClass bc = new BadgeClass(badgeForm.get().name,
				badgeForm.get().description, badgeForm.get().image,
				badgeForm.get().criteria, issuerURL, issuerId);

		bc.save();

		bc.setTags(tags);

		flash(Application.GLOBAL_FLASH_SUCCESS, "Badge added");

		return redirect(routes.BadgeController.badges());
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
//	public static Result getJson(Long id) {
//		BadgeClass jsonBadge = BadgeClass.find.byId(id);
//		return ok(Json.toJson(jsonBadge));
//	}

	public static Result delete(Long id) {
		// Delete badge children (tags)
		List<Tag> tags = Tag.find.where().eq("assignedTo.id", id).findList();
		for (Tag t : tags) {
			t.delete();
		}
		BadgeClass.find.byId(id).delete();
		flash(Application.GLOBAL_FLASH_SUCCESS, "Badge deleted");
		return redirect(routes.BadgeController.badges());
	}

}
