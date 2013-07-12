package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class Revocation extends Model {

	public Long uid;
	public String reason;

	public static Model.Finder<Long, Revocation> find = new Model.Finder<Long, Revocation>(
			Long.class, Revocation.class);
}
