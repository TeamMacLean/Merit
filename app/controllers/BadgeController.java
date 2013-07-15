package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.*;
import models.Image.imageType;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class BadgeController extends Controller {

	public static Result test() {
		return ok(test.render());
	}

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

		return ok(badges.render(badgesList, images, issuers, badgeForm));
	}

	public static Result addBadge() {

		Form<BadgeClass> badgeForm = new Form<BadgeClass>(BadgeClass.class)
				.bindFromRequest();

		if (badgeForm.hasErrors()) {
			return TODO;
		}

		String tagsOneLine = badgeForm.get().tagsOneLine;

		// TODO the arraylist is being populated but the data is not accessable
		// later.

		List<String> tags = Arrays.asList(tagsOneLine.split("\\s*,\\s*"));

		Logger.info("TAGS, INPUT= " + tagsOneLine);

		// Logger.info("TAGS, List=");

		String issuerIdString = badgeForm.get().issuerString;

		Long issuerId = Long.parseLong(issuerIdString);

		if (issuerId == null) {
			Logger.error("issuerId is EFFED up!");
		}

		String issuerURL = routes.IssuerController.getIssuerJson(issuerId)
				.absoluteURL(request());

		BadgeClass bc = new BadgeClass(badgeForm.get().name,
				badgeForm.get().description, badgeForm.get().image, null,
				issuerURL, null);

		bc.save();

		bc.setTags(tags);

		return redirect(routes.BadgeController.badges());
	}

	public static Result delete(Long id) {
		// Delete badge children (tags)
		List<Tag> tags = Tag.find.where().eq("assignedTo.id", id).findList();
		for (Tag t : tags) {
			t.delete();
		}
		BadgeClass.find.byId(id).delete();
		return redirect(routes.BadgeController.badges());
	}

}
