package com.appserversrv.activities;

import com.serverservice.test.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String str = "com.service.server";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //创建启动service的Intent  
        final Intent intent = new Intent();  
        //为Intent设置Action属性  
        intent.setAction(str);  
        
        View view = (Button) findViewById(R.id.btnstart);
        if (null != view) {
        	view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					 startService(intent);
				}});
        }
        view = (Button) findViewById(R.id.btnstop);   
        if (null != view) {
        	view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					 stopService(intent);
				}});
        }
        //获取wifi服务  
        android.net.wifi.WifiManager wifimanager = (android.net.wifi.WifiManager) getSystemService(Context.WIFI_SERVICE);  
        //判断wifi是否开启  
        if (!wifimanager.isWifiEnabled()) {  
        	wifimanager.setWifiEnabled(true);    
        }  
        android.net.wifi.WifiInfo wifiinfo = wifimanager.getConnectionInfo();       
        int ipAddress = wifiinfo.getIpAddress();   
        String ip = intToIp(ipAddress);   
        TextView et = (TextView)findViewById(R.id.txtTitle);
        if (null != et) {
            et.setText(ip);        	
        }  
    }
    private String intToIp(int i) {  
        return (i&0xFF) + "." + ((i>>8)&0xFF) + "." + ((i>>16)&0xFF) + "." + (i>>24&0xFF);  
   }   
}
