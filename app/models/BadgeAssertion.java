package models;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.joda.time.DateTime;

import play.db.ebean.Model;

@Entity
public class BadgeAssertion extends Model {

	@Id
	public String uid;

	public IdentityObject recipient;

	public URL image;

	public URL evidence;

//	@play.data.format.Formats.DateTime(pattern = "")
	public DateTime issuedOn; // Either an ISO 8601 date or a standard 10-digit
								// Unix timestamp.

	public URL badge;

	public VerificationObject verify;

//	@play.data.format.Formats.DateTime(pattern = "")
	public DateTime expires; // Either an ISO 8601 date or a standard 10-digit
								// Unix timestamp.

	public BadgeAssertion(IdentityObject recipient, URL badge,
			VerificationObject verify, DateTime issuedOn, URL image,
			URL evidence, DateTime expires) {

		this.recipient = recipient;
		this.badge = badge;
		this.verify = verify;
		this.issuedOn = issuedOn;
		this.image = image;
		this.evidence = evidence;
		this.expires = expires;

	}

	public static Model.Finder<Long, BadgeAssertion> find = new Model.Finder<Long, BadgeAssertion>(
			Long.class, BadgeAssertion.class);
}
