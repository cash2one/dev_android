package base.utils;

public class log {

	private final static boolean mIsShowLog = true;
	public static String TAG = "log";
	
	public static void v(String tag, String content) {
		if (mIsShowLog)
			android.util.Log.v(tag, content);
	}

	public static void i(String tag, String content) {
		if (mIsShowLog)
			android.util.Log.i(tag, content);
	}

	public static void d(String tag, String content) {
		if (mIsShowLog)
			android.util.Log.d(tag, content);
	}

	public static void w(String tag, String content) {
		if (mIsShowLog)
			android.util.Log.w(tag, content);
	}

	public static void e(String tag, String content) {
		if (mIsShowLog)
			android.util.Log.e(tag, content);
	}
	
	public static void v(String content) {
		v(TAG, content);
	}

	public static void i(String content) {
		i(TAG, content);
	}

	public static void d(String content) {
		d(TAG, content);
	}

	public static void w(String content) {
		w(TAG, content);
	}

	public static void e(String content) {
		e(TAG, content);
	}
}
