import play.*;

import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if (User.find.findRowCount() == 0) {

				User ADMIN = new User();
				ADMIN.email = "martin.page@tsl.ac.uk";
				ADMIN.name = "ADMIN";
				ADMIN.password = "B0nF1rE";
				ADMIN.apiKey = "jfsoidfj098SDFJI0sdfjSDf9Ss8204hSDfj923h8ejg5wvIrcYfn9yv8";
				ADMIN.save();

			}
		}

	}

}