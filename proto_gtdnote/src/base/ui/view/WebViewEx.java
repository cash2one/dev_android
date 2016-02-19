package base.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebViewClient;

public class WebViewEx extends android.webkit.WebView {

	//*/
	public WebViewEx(Context context) {
		super(context);
		mContext = context;
	}
	
	public WebViewEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public WebViewEx(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	public Context mContext = null;
    public WebViewClientEx viewClient = null;
    public WebChromeClientEx chromeClient = null;
    

    // Package visibility to enforce that only SystemWebViewEngine should call this method.
    public void init() {
        if (null == this.viewClient) {
            setWebViewClient(new WebViewClientEx(this));
        }

        if (null == this.chromeClient) {
            setWebChromeClient(new WebChromeClientEx(this));
        }
        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);  //支持js
        //settings.setPluginsEnabled(true);  //支持插件 
        //settings.setUseWideViewPort(false);  //将图片调整到适合webview的大小 
        //settings.setSupportZoom(true);  //支持缩放 
        //settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局  
        //settings.supportMultipleWindows();  //多窗口 
        //settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存 
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存
        settings.setAppCacheEnabled(false);
        settings.setAllowFileAccess(true);  //设置可以访问文件 
        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        //settings.setBuiltInZoomControls(true); //设置支持缩放 
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口 
        //settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        
        //String path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/gefdemoweb";
        //settings.setAppCachePath(path);
        this.addJavascriptInterface(this, "webview");//对应js中的test.xxx
        
        CookieSyncManager.createInstance(mContext);
        CookieManager cookiemgr = CookieManager.getInstance();
        cookiemgr.setAcceptCookie(true);
        CookieSyncManager.getInstance().sync();
        //cookiemgr.setCookie(url, cookies);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        viewClient = (WebViewClientEx)client;
        super.setWebViewClient(client);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        chromeClient = (WebChromeClientEx)client;
        super.setWebChromeClient(client);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //Boolean ret = parentEngine.client.onDispatchKeyEvent(event);
    	//if (ret != null) {
    	//  return ret.booleanValue();
    	//}
        return super.dispatchKeyEvent(event);
    }
    
    @JavascriptInterface
    public void hello(String msg){//对应js中xxx.hello("")
        //Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
