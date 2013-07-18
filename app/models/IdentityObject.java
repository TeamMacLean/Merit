package models;

import javax.persistence.Entity;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class IdentityObject extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171114029087937406L;
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

}
