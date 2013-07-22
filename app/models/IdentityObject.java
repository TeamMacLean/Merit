package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class IdentityObject extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171114029087937406L;
	@Id
	@JsonIgnore
	public Long id;
	@Required
	public IdentityType type;
	@Required
	public boolean hashed;
	@Required
	public String salt;
	@Required
	public String identity; // or IdentityHash

	public IdentityObject(IdentityHash identity, IdentityType type,
			boolean hashed, String salt) {
		this.identity = identity.getDigest();
		this.type = type;
		this.hashed = hashed;
		this.salt = salt;

	}
	public static Model.Finder<Long, IdentityObject> find = new Model.Finder<Long, IdentityObject>(
			Long.class, IdentityObject.class);
}
