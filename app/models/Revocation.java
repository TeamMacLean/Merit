package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Revocation extends Model {

	@Id
	public Long uid;
	@Required
	public String reason;

	public static Model.Finder<Long, Revocation> find = new Model.Finder<Long, Revocation>(
			Long.class, Revocation.class);
}
