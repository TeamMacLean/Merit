package models;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Image extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3002036419433089182L;
	public static final String issuers = "issuers";
	public static final String badges = "badges";
	public static final String lostandfound = "lostandfound";

	public static final String imagesFolder = File.separator+"public"+File.separator+"images"+File.separator;

	@Id
	public Long id;
	public String url;
	@Required
	public imageType imageType;

	public enum imageType {
		issuer, badge
	}

	@Required
	public String name;

	public Image(String url, String name, imageType imageType) {
		this.url = url;
		this.name = name;
		this.imageType = imageType;
	}

	// public URL getURL() {
	// URL publicURL = null;
	// try {
	// publicURL = new URL(url);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// return publicURL;
	// }

	public static Model.Finder<Long, Image> find = new Model.Finder<Long, Image>(
			Long.class, Image.class);
}
