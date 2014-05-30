package models;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

import play.Logger;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class BadgeClass extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6484951202060801245L;

	@Id
	@JsonIgnore
	public Long id;

	@Required
	public String name;
	@Required
	public String description;
	@Required
	public String image;
	@Required
	public URL criteria;
//	@Required
	public String issuer; // Endpoint should be an IssuerOrganization
	@JsonIgnore
	public Long alignment;

	@JsonIgnore
	@Required
	public String tagsOneLine;

	@JsonIgnore
	@Required
	public String alignmentString;

	@JsonIgnore
	@Required
	public String issuerString;

	public BadgeClass(String name, String description, String image,
			URL criteria, String issuer, Long alignment) {

		this.name = name;
		this.description = description;
		this.image = image;
		this.criteria = criteria;
		this.issuer = issuer;
		this.alignment = alignment;

	}

	public AlignmentObject getAlignment() {
		return AlignmentObject.find.byId(alignment);
	}

	public ArrayList<String> getTags() {

		List<Tag> tags = Tag.find.where().eq("assignedTo.id", id).findList();

		ArrayList<String> values = new ArrayList<String>();

		for (Tag t : tags) {
			values.add(t.value);
		}
		return values;
	}

	@JsonIgnore
	public String getTagsString() {
		List<Tag> tags = Tag.find.where().eq("assignedTo.id", id).findList();

		String tagList = "";
		for (Tag s : tags) {
			tagList += s.value + ", ";
		}

		return tagList;
	}

	public static Model.Finder<Long, BadgeClass> find = new Model.Finder<Long, BadgeClass>(
			Long.class, BadgeClass.class);

	public void setTags(List<String> tags) {
		for (String t : tags) {
			Logger.info("adding: " + t);
			new Tag(t, this).save();
		}
	}

}
