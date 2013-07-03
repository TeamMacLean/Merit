package models;

import java.net.URL;

import javax.persistence.Entity;

import org.joda.time.DateTime;

@Entity
public class BadgeAssertion {

	String uid;
	IdentityObject recipient;
	URL badge;
	VerificationObject verify;
	DateTime issuedOn;
	BadgeImage image;
	URL evidence;
	DateTime expires;
}
