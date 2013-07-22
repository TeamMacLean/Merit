package models;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.libs.Json;

@Entity
public class BadgeAssertion extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826018532497499299L;
	@Id
	public Long uid;
	@Required
	@JsonIgnore
	public Long recipient;
	@Required
	public URL evidence;

	public Long issuedOn = System.currentTimeMillis() / 1000L; // Either an ISO
																// 8601 date or
																// a standard
																// 10-digit
	// Unix timestamp.
	@Required
	public URL badge;

	@Required
	@JsonIgnore
	public Long verify;

	@JsonSerialize
	public IdentityObject getRecipient() {
		// return Json.toJson(IdentityObject.find.byId(verify));
		return IdentityObject.find.byId(verify);
	}

	@JsonSerialize
	public VerificationObject getVerify() {
		return VerificationObject.find.byId(verify);
	}

	public BadgeAssertion(Long recipient, URL badge, Long verify, URL evidence) {

		this.recipient = recipient;
		this.badge = badge;
		this.verify = verify;
		this.evidence = evidence;

	}

	public static Model.Finder<Long, BadgeAssertion> find = new Model.Finder<Long, BadgeAssertion>(
			Long.class, BadgeAssertion.class);
}
