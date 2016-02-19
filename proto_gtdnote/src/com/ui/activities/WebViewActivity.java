package com.ui.activities;

import module.res.ComRes;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import base.ui.BaseActivity;
import base.ui.view.WebViewEx;

public class WebViewActivity  extends BaseActivity{

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ComRes.layoutId_WebViewActivity());
        
        RelativeLayout rootview = (RelativeLayout )findViewById(ComRes.layoutId_rootView());
        if (null != rootview) {
        	mWebView = new WebViewEx(this);   
        	rootview.addView(mWebView, new RelativeLayout.LayoutParams(
        			RelativeLayout.LayoutParams.MATCH_PARENT, 
        			RelativeLayout.LayoutParams.MATCH_PARENT));
        	mWebView.init();
        	//mWebView.loadUrl("http://www.baidu.com");
        	mWebView.loadUrl("http://www.zhijiaoyi.com/w/Index/index/");
        	//mWebView.loadUrl("file:///android_asset/js_web.html");
        }        
    }
    
    private WebViewEx mWebView = null;
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
        	mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
