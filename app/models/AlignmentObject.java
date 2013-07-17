package models;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class AlignmentObject extends Model {

	public AlignmentObject(String name, URL url, String description) {
		this.name = name;
		this.url = url;
		this.description = description;
	}

	@Id
	public Long id;
	@Required
	public String name;
	@Required
	public URL url;
	@Required
	public String description;

	public static Model.Finder<Long, AlignmentObject> find = new Model.Finder<Long, AlignmentObject>(
			Long.class, AlignmentObject.class);

}
