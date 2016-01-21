package com.appdone;

import base.app.AppInst;
import base.utils.log;

public class App extends AppInst {

	private static final String LOGTAG = App.class.getSimpleName();
	public static App instance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		log.d(LOGTAG, "App on create");
		//core = ManagerFactory.getManagerFactory();
		//Thread.setDefaultUncaughtExceptionHandler(new com.base.exception.LocalFileHandler(this));
    }
}
