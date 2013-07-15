package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tag extends Model {

	@Id
	public Long id;
	
	public String value;

	@ManyToOne
	public BadgeClass assignedTo;
	
	public static Model.Finder<Long, Tag> find = new Model.Finder<Long, Tag>(
			Long.class, Tag.class);
	
	public String value(){
		Logger.info("value= "+value);
		return value;
	}
	
	public Tag(String t, BadgeClass badgeClass) {
		this.value = t;
		this.assignedTo = badgeClass;
	}
}