package models;

import java.net.URL;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class VerificationObject extends Model {

	public VerificationType type; // TODO TYPE
	public URL url;

	public VerificationObject(VerificationType type, URL url) {
		this.type = type;
		this.url = url;
	}

}
