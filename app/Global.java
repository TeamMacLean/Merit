import java.lang.reflect.Method;

import play.*;
import play.mvc.Action;
import play.mvc.Http.Request;

import models.*;

public class Global extends GlobalSettings {

	@Override
	public Action onRequest(Request arg0, Method arg1) {
		
		// TODO Auto-generated method stub
		Logger.info("---Remote address = "+arg0.remoteAddress());
		Logger.info("---Request URI = "+arg0.uri());
//		for(String[] s: arg0.headers().values()){
//			for(String ss: s){
//				Logger.info("REQUEST HEADER: "+ss);
//			}
//		}
		return super.onRequest(arg0, arg1);
	}
	
	public void onStart(Application app) {
		Logger.info("Starting Merit");
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