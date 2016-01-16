package base.app;

public class AppInst extends android.app.Application {

	public static AppInst instance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//core = ManagerFactory.getManagerFactory();
		//Thread.setDefaultUncaughtExceptionHandler(new com.base.exception.LocalFileHandler(this));
    }

	public static void showToast(String message) {		
	};
}
