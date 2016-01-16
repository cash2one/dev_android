package com.appserversrv.activities;

import com.serverservice.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    }
}
