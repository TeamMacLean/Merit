package models;

import java.net.URL;

import javax.persistence.Entity;

@Entity
public class IssuerOrganization {

	String name;
	URL url;
	String description;
	URL image;
	String email;
	URL revocationList;
	
}
