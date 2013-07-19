package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

import com.avaje.ebean.*;

@Entity
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5814216316330145793L;

	@Id
	public Long id;

	@Required
	@Formats.NonEmpty
	public String email;

	@Required
	public String name;

	@Required
	public String password;

	public String apiKey;

	// -- Queries

	public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(
			Long.class, User.class);

	public static List<User> findAll() {
		return find.all();
	}

	public static User findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	public static User findById(Long id) {
		return find.byId(id);
	}

	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password)
				.findUnique();
	}

	public String toString() {
		return "User(" + email + ")";
	}

}
