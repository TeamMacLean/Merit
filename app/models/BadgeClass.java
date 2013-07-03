package models;

import java.net.URL;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class BadgeClass {

	String name;
	String description;
	URL image;
	URL criteria;
	URL issuer; //Endpoint should be an IssuerOrganization
	List<AlignmentObject> alignment;
	List<String> tags;
	
}
