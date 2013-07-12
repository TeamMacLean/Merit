package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Files;

import models.*;
import models.Image.imageType;
import play.*;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;
import play.libs.Json;

public class AdminController extends Controller {

	public static Result index() {
		return ok(admin.render());
	}

	public static Result assertions() {
		// list assertions

		List<BadgeAssertion> assertionsList = BadgeAssertion.find.all();

		return TODO;
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
		// for(String t:tags){
		// Logger.info("TAG: "+t);
		// }

		String issuerIdString = badgeForm.get().issuerString;

		Long issuerId = Long.parseLong(issuerIdString);

		if (issuerId == null) {
			Logger.error("issuerId is EFFED up!");
		}

		String issuerURL = routes.AdminController.getIssuerJson(issuerId)
				.absoluteURL(request());

		new BadgeClass(badgeForm.get().name, badgeForm.get().description,
				badgeForm.get().image, null, issuerURL, null, tags).save();

		return redirect(routes.AdminController.badges());
	}

	public static Result getIssuerJson(Long id) {
		IssuerOrganization io = IssuerOrganization.find.byId(id);
		return ok(Json.toJson(io));
	}

	public static Result images() {

		Form<Image> imagesForm = new Form<Image>(Image.class);

		List<Image> imagesList = Image.find.all();

		return ok(images.render(imagesList, imagesForm));
	}

	public static Result addImage() {

		// TODO THIS IS HORRIBLE CODE!! NEED TO REDO!

		Form<Image> imagesForm = new Form<Image>(Image.class).bindFromRequest();

		if (imagesForm.hasErrors()) {
			List<Image> imagesList = Image.find.all();
			return badRequest(images.render(imagesList, imagesForm));
		}

		MultipartFormData body = request().body().asMultipartFormData();

		if (body == null) {
			Logger.error("body is null");
		}

		FilePart resourceFile = body.getFile("imageFile");

		String imagesPath = Image.imagesFolder;
		String selectedFolder = Image.lostandfound;

		if (imagesForm.get().imageType == imageType.badge) {
			selectedFolder = Image.badges;
		} else if (imagesForm.get().imageType == imageType.issuer) {
			selectedFolder = Image.issuers;
		}

		File projectRoot = Play.application().path();

		File fullPath = new File(projectRoot, imagesPath + selectedFolder);

		File newLoc = new File(fullPath, resourceFile.getFilename());

		
		try {
			Files.move(resourceFile.getFile(), newLoc);
		} catch (IOException e) {
			e.printStackTrace();
			return badRequest("We could not move the file, Sorry.");
		}

		File imagesFolder = new File("images");

		File relativeImagesPath = new File(imagesFolder, selectedFolder);

		File relativePath = new File(relativeImagesPath,
				resourceFile.getFilename());

		String absURL = routes.Assets.at(relativePath.getPath()).absoluteURL(
				request());

		new Image(absURL, imagesForm.get().name, imagesForm.get().imageType)
				.save();

		return redirect(routes.AdminController.images());
	}

	public static Result issuers() {
		Form<IssuerOrganization> imagesForm = new Form<IssuerOrganization>(
				IssuerOrganization.class);
		List<IssuerOrganization> issuersList = IssuerOrganization.find.all();

		List<Image> imagesList = Image.find.where()
				.eq("imageType", imageType.issuer).findList();

		return ok(issuers.render(issuersList, imagesList, imagesForm));
	}

	public static Result addIssuer() {
		Form<IssuerOrganization> issuersForm = new Form<IssuerOrganization>(
				IssuerOrganization.class).bindFromRequest();

		if (issuersForm.hasErrors()) {
			List<IssuerOrganization> issuersList = IssuerOrganization.find
					.all();
			List<Image> imagesList = Image.find.where()
					.eq("imageType", imageType.issuer).findList();
			return badRequest(issuers.render(issuersList, imagesList,
					issuersForm));
		}

		String recovationURL = RevocationController.getUrl(request());

		new IssuerOrganization(issuersForm.get().name, issuersForm.get().url,
				issuersForm.get().description, issuersForm.get().image,
				issuersForm.get().email, recovationURL).save();

		return redirect(routes.AdminController.issuers());
	}

}
