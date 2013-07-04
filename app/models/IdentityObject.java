package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class IdentityObject extends Model {

	public IdentityType type;
	public boolean hashed;
	public String salt;
	public String identity; // or IdentityHash

	public IdentityObject(IdentityHash identity, IdentityType type,
			boolean hashed, String salt) {
		this.identity = identity.getDigest();
		this.type = type;
		this.hashed = hashed;
		this.salt = salt;

	}

}
