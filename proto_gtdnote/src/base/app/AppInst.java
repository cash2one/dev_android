package base.app;

import base.utils.*;

public class AppInst extends android.app.Application {

	public static AppInst instance = null;
	private static final String LOGTAG = AppInst.class.getSimpleName();
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//core = ManagerFactory.getManagerFactory();
		//Thread.setDefaultUncaughtExceptionHandler(new com.base.exception.LocalFileHandler(this));
    }

	public static void quitApp() {
		android.os.Process.killProcess(android.os.Process.myPid());    //获取PID 
		System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
	}
	/*
	public static void quitApp2() {
		//系统会将，该包下的 ，所有进程，服务，全部杀掉，就可以杀干净了，要注意加上 
        //<uses-permission android:name=\"android.permission.RESTART_PACKAGES\"></uses-permission>
		android.app.ActivityManager am = (android.app.ActivityManager) instance.getSystemService(android.content.Context.ACTIVITY_SERVICE);
	    am.restartPackage(instance.getPackageName()); 
	}
	
	public static void quitApp3() {
		// Android的窗口类提供了历史栈，我们可以通过stack的原理来巧妙的实现，
		// 这里我们在A窗口打开B窗口时在Intent中直接加入标 志     Intent.FLAG_ACTIVITY_CLEAR_TOP，
		// 这样开启B时将会清除该进程空间的所有Activity
		Intent intent = new Intent(); 
		//intent.setClass(instance, QuotAppActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置 
		instance.startActivity(intent);
		//接下来在B窗口中需要退出时直接使用finish方法即可全部退出
	}
	
	public static void quitApp4 () {
		try {
			BaseApp.core.getActivitiesManager().finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) instance.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(instance.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}	
	}
	
	public static void quitApp() { 
		quitApp4();
	}*/


	private static android.os.Handler mAppMsgHandler = null;
	protected android.os.Handler getMessageHandler() {
		if (null != mAppMsgHandler)
			return mAppMsgHandler;
		android.os.Looper loop = android.os.Looper.getMainLooper();
		return mAppMsgHandler = new android.os.Handler(loop) {
			@Override
			public void handleMessage(android.os.Message msg) {
				doHandleMessage(msg);
				super.handleMessage(msg);
				// AppManager.handleAppMessage(msg);
			}
		};
	}

	private void doHandleMessage(android.os.Message msg) {
		switch (msg.what) {
		case AppConsts.MSG_TOAST:
			log.d(LOGTAG, " handleAppMessage " + msg.what);
			android.widget.Toast.makeText(instance, (String)msg.obj, 
					android.widget.Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
	public static void sendAppMessage(int msg, Object obj) {
		android.os.Message msgobj = instance.getMessageHandler().obtainMessage();
		msgobj.obj = obj;
		msgobj.what = msg;
		msgobj.sendToTarget();
	}

	public void sendBroadcast(String MsgID, String extraName, String extraData) {
		if (UtilsStr.isEmpty(MsgID))
			return;
		android.content.Intent intent = new android.content.Intent(MsgID);
		if (!UtilsStr.isEmpty(extraName)) {
			intent.putExtra(extraName, extraData);
		}
		instance.sendBroadcast(intent);
	}
	
	public static void showToast(String message) {	
		if (UtilsStr.isEmpty(UtilsStr.strNoNull(message)))
			return;
		log.d(LOGTAG, "showToast:" + message);
		sendAppMessage(AppConsts.MSG_TOAST, message);	
	};
}
