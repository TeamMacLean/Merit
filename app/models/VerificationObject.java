package models;

import java.net.URL;

import javax.persistence.Entity;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class VerificationObject extends Model {

	@Required
	public VerificationType type; // TODO TYPE
	@Required
	public URL url;

	public VerificationObject(VerificationType type, URL url) {
		this.type = type;
		this.url = url;
	}

}
