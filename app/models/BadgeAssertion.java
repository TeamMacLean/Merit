package models;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class BadgeAssertion extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826018532497499299L;
	@Id
	public String uid;
	@Required
	public IdentityObject recipient;
	@Required
	public URL image;
	@Required
	public URL evidence;

	public Long issuedOn = System.currentTimeMillis() / 1000L; // Either an ISO
																// 8601 date or
																// a standard
																// 10-digit
	// Unix timestamp.

	public URL badge;

	public VerificationObject verify;

	public BadgeAssertion(IdentityObject recipient, URL badge,
			VerificationObject verify, URL image, URL evidence) {

		this.recipient = recipient;
		this.badge = badge;
		this.verify = verify;
		// this.issuedOn = issuedOn;
		this.image = image;
		this.evidence = evidence;

	}

	public static Model.Finder<Long, BadgeAssertion> find = new Model.Finder<Long, BadgeAssertion>(
			Long.class, BadgeAssertion.class);
}
