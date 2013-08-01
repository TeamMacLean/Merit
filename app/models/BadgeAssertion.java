package models;

import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class BadgeAssertion extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826018532497499299L;
	
	@Id
	@JsonIgnore
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

	@JsonIgnore
	public Boolean blocked = false;
	
	@Required
	@JsonIgnore
	public Long verify;

	@JsonSerialize
	public String getUid(){
		return uid.toString();
	}
	
	@JsonSerialize
	public IdentityObject getRecipient() {
		// return Json.toJson(IdentityObject.find.byId(verify));
		return IdentityObject.find.byId(verify);
	}

	@JsonSerialize
	public VerificationObject getVerify() {
		return VerificationObject.find.byId(verify);
	}

	@JsonIgnore
	public String getRealTime(){
		return new java.util.Date((long)issuedOn*1000).toString();
	}
	
	public void setBlocked(){
		blocked = true;
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
