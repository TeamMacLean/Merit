package models;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Image extends Model {

	public static final String issuers = "issuers";
	public static final String badges = "badges";
	public static final String lostandfound = "lostandfound";

	public static final String imagesFolder = "/public/images/";

	@Id
	public Long id;

	public String url;

	public imageType imageType;

	public enum imageType {
		issuer, badge
	}

	public String name;

	public Image(String url, String name, imageType imageType) {
		this.url = url;
		this.name = name;
		this.imageType = imageType;
	}

//	public URL getURL() {
//		URL publicURL = null;
//		try {
//			publicURL = new URL(url);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		return publicURL;
//	}

	public static Model.Finder<Long, Image> find = new Model.Finder<Long, Image>(
			Long.class, Image.class);
}
