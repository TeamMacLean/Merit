package models;

import java.net.URL;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

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
	public List<String> tags;
	
	@JsonIgnore
	public String tagsOneLine;

	@JsonIgnore
	public String issuerString;
	
	public BadgeClass(String name, String description, String image, URL criteria,
			String issuer, List<AlignmentObject> alignment, List<String> tags) {

		this.name = name;
		this.description = description;
		this.image = image;
		this.criteria = criteria;
		this.issuer = issuer;
		this.alignment = alignment;
		this.tags = tags;
		
	}

	public static Model.Finder<Long, BadgeClass> find = new Model.Finder<Long, BadgeClass>(
			Long.class, BadgeClass.class);

}
