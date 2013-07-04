package models;

import java.net.URL;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class AlignmentObject extends Model {

	String name;
	URL url;
	String description;

}
