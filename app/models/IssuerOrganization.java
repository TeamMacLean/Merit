package models;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class IssuerOrganization extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1475362675989303025L;
	@Id
	@JsonIgnore
	public Long id;
	@Required
	public String name;
	@Required
	public URL url;
	@Required
	public String description;
	@Required
	public String image;
	
	@Email
	public String email;
	public String revocationList;

	public IssuerOrganization(String name, URL url, String description,
			String image, String email, String recovationURL) {
		this.name = name;
		this.url = url;
		this.description = description;
		this.image = image;
		this.email = email;
		this.revocationList = recovationURL;
	}

	public static Model.Finder<Long, IssuerOrganization> find = new Model.Finder<Long, IssuerOrganization>(
			Long.class, IssuerOrganization.class);

}
