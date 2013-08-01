package controllers;

import java.util.List;

import models.Image;
import models.IssuerOrganization;
import models.Image.imageType;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.issuers;
import play.mvc.*;

@Security.Authenticated(Secured.class)
public class IssuerController extends Controller {

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

		flash(Application.GLOBAL_FLASH_SUCCESS, "Issuer added");
		return redirect(routes.IssuerController.issuers());
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
//	public static Result getJson(Long id) {
//		IssuerOrganization io = IssuerOrganization.find.byId(id);
//		return ok(Json.toJson(io));
//	}

	public static Result delete(Long id) {
		IssuerOrganization.find.byId(id).delete();
		flash(Application.GLOBAL_FLASH_SUCCESS, "Issuer deleted");
		return redirect(routes.IssuerController.issuers());
	}
}
