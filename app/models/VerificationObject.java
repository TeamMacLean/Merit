package models;

import java.net.URL;

import javax.persistence.Entity;

@Entity
public class VerificationObject {

	public VerificationType type; // TODO TYPE
	public URL url;

	public VerificationObject(VerificationType type, URL url) {
		this.type = type;
		this.url = url;
	}

}
