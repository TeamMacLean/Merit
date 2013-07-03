package models;

import javax.persistence.Entity;

@Entity
public class IdentityObject {

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
