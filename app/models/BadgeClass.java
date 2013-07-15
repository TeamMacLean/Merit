package models;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.Logger;
import play.db.ebean.Model;

@Entity
public class BadgeClass extends Model {

	@Id
	@JsonIgnore
	public Long id;

	public String name;
	public String description;
	public String image;
	public URL criteria;
	public String issuer; // Endpoint should be an IssuerOrganization
	public List<AlignmentObject> alignment;

	// @ManyToMany(cascade = CascadeType.ALL)
	// public List<Tag> tags = new ArrayList<Tag>();

	@JsonIgnore
	public String tagsOneLine;

	@JsonIgnore
	public String issuerString;

	public BadgeClass(String name, String description, String image,
			URL criteria, String issuer, List<AlignmentObject> alignment) {

		this.name = name;
		this.description = description;
		this.image = image;
		this.criteria = criteria;
		this.issuer = issuer;
		this.alignment = alignment;

	}

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
