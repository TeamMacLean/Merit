package models;

import java.net.URL;

import javax.persistence.Entity;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class VerificationObject extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5831106201472961930L;
	@Required
	public VerificationType type; // TODO TYPE
	@Required
	public URL url;

	public VerificationObject(VerificationType type, URL url) {
		this.type = type;
		this.url = url;
	}

}
