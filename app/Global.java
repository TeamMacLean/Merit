import play.*;

import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if (User.find.findRowCount() == 0) {

				User ADMIN = new User("ADMIN", "martin.page@tsl.ac.uk",
						"B0nF1rE");
				ADMIN.save();

			}
		}

	}

}