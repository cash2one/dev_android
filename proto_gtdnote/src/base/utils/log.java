package base.utils;

import android.util.Log;

/**
 * 类说明 日志打印，用于开发与调试，正式版发布时请把log开关关上。
 */
public class log {

	/** 日志打印开关，开发与调试为true，在正式版发布时置为false */
	private final static boolean showLog = true;

	/** 统一的标签 */
	private final static String TAG = "ZhiJiaoYi";

	/**
	 * VERBOSE日志，显示为黑色
	 * 
	 * @param tag
	 *            标签，可以为类名，可以为对象名，也可以为自己自定义的标签
	 * @param content
	 *            日志内容
	 */
	public static void v(String tag, String content) {
		if (showLog)
			log.v(tag, content);
	}

	/**
	 * INFO日志，显示为绿色
	 * 
	 * @param tag
	 *            标签，可以为类名，可以为对象名，也可以为自己自定义的标签
	 * @param content
	 *            日志内容
	 */
	public static void i(String tag, String content) {
		if (showLog)
			log.i(tag, content);
	}

	/**
	 * DEBUG日志，显示为蓝色
	 * 
	 * @param tag
	 *            标签，可以为类名，可以为对象名，也可以为自己自定义的标签
	 * @param content
	 *            日志内容
	 */
	public static void d(String tag, String content) {
		if (showLog)
			log.d(tag, content);
	}

	/**
	 * WARNING日志，显示为橘色
	 * 
	 * @param tag
	 *            标签，可以为类名，可以为对象名，也可以为自己自定义的标签
	 * @param content
	 *            日志内容
	 */
	public static void w(String tag, String content) {
		if (showLog)
			log.w(tag, content);
	}

	/**
	 * ERROR日志，显示为红色
	 * 
	 * @param tag
	 *            标签，可以为类名，可以为对象名，也可以为自己自定义的标签
	 * @param content
	 *            日志内容
	 */
	public static void e(String tag, String content) {
		if (showLog)
			log.e(tag, content);
	}

	/**
	 * VERBOSE日志，显示为黑色
	 * 
	 * @param content
	 *            日志内容
	 */
	public static void v(String content) {
		v(TAG, content);
	}

	/**
	 * INFO日志，显示为绿色
	 * 
	 * @param content
	 *            日志内容
	 */
	public static void i(String content) {
		i(TAG, content);
	}

	/**
	 * DEBUG日志，显示为蓝色
	 * 
	 * @param content
	 *            日志内容
	 */
	public static void d(String content) {
		d(TAG, content);
	}

	/**
	 * WARNING日志，显示为橘色
	 * 
	 * @param content
	 *            日志内容
	 */
	public static void w(String content) {
		w(TAG, content);
	}

	/**
	 * ERROR日志，显示为红色
	 * 
	 * @param content
	 *            日志内容
	 */
	public static void e(String content) {
		e(TAG, content);
	}
}
