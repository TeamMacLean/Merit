package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.Constraints.Required;

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

//	@Required
	public String password;

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
	
	public User(String name, String email){
		this.name = name;
		this.email = email;
		this.password = genPassword(); //This means they are a new user with a randomly generated password (will be emailed to them)
	}

	public User(String name, String email, String password){
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	private String genPassword() {
        int len=8;
        char[] pwd = new char[len];
        int c = 'A';
        int rand = 0;
        for (int i=0; i < 8; i++)
        {
            rand = (int)(Math.random() * 3);
            switch(rand) {
                case 0: c = '0' + (int)(Math.random() * 10); break;
                case 1: c = 'a' + (int)(Math.random() * 26); break;
                case 2: c = 'A' + (int)(Math.random() * 26); break;
            }
            pwd[i] = (char)c;
        }
        return new String(pwd);
//		return null;
	}

}
