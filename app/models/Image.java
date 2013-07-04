package models;

import java.io.File;

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

	public static Model.Finder<Long, Image> find = new Model.Finder<Long, Image>(
			Long.class, Image.class);
}
