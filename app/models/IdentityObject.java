package models;

import javax.persistence.Entity;

@Entity
public class IdentityObject {

	IdentityHash identity; //or IdentityHash
	IdentityType type;
	boolean hashed;
	String salt;
	
}
