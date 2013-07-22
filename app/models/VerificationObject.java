package models;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class VerificationObject extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5831106201472961930L;
	@Id
	@JsonIgnore
	public Long id;
	@Required
	public VerificationType type;
	@Required
	public URL url;

	public VerificationObject(VerificationType type, URL url) {
		this.type = type;
		this.url = url;
	}
	public static Model.Finder<Long, VerificationObject> find = new Model.Finder<Long, VerificationObject>(
			Long.class, VerificationObject.class);
}
