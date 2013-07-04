package models;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.db.ebean.Model;

@Entity
public class IssuerOrganization extends Model {

	@Id
	private Long id;
	
	public String name;
	public URL url;
	public String description;
	public URL image;
	
	@Email
	public String email;
	
	public URL revocationList;

	public IssuerOrganization(String name, URL url, String description,
			URL image, String email, URL revocationList) {
		this.name = name;
		this.url = url;
		this.description = description;
		this.image = image;
		this.email = email;
		this.revocationList = revocationList;
	}

	public static Model.Finder<Long, IssuerOrganization> find = new Model.Finder<Long, IssuerOrganization>(
			Long.class, IssuerOrganization.class);
}
