package controllers;

import models.AlignmentObject;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.mvc.*;

@Security.Authenticated(Secured.class)
public class AlignmentController extends Controller {

	public static Result alignments() {

		Form<AlignmentObject> alignmentForm = new Form<AlignmentObject>(
				AlignmentObject.class);
		return ok(alignments.render(AlignmentObject.find.findList(),
				alignmentForm));
	}

	public static Result addAlignment() {
		Form<AlignmentObject> alignmentForm = new Form<AlignmentObject>(
				AlignmentObject.class).bindFromRequest();
		if (alignmentForm.hasErrors()) {
			return badRequest(alignments.render(
					AlignmentObject.find.findList(), alignmentForm));
		} else {
			AlignmentObject ao = new AlignmentObject(alignmentForm.get().name,
					alignmentForm.get().url, alignmentForm.get().description);
			ao.save();
			flash(Application.GLOBAL_FLASH_SUCCESS, "Alignment added");
			return redirect(routes.AlignmentController.alignments());
		}
	}

	public static Result delete(Long id) {
		AlignmentObject.find.byId(id).delete();
		flash(Application.GLOBAL_FLASH_SUCCESS, "Alignment deleted");
		return redirect(routes.AlignmentController.alignments());
	}

//	@BodyParser.Of(play.mvc.BodyParser.Json.class)
//	public static Result getJson(Long id) {
//		AlignmentObject ao = AlignmentObject.find.byId(id);
//		return ok(Json.toJson(ao));
//	}

}
