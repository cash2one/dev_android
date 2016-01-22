package com.appdone.activities;

import com.appdone.Res;
import com.done.R;
import com.ui.activities.AppEmptyActivity;

import android.view.View;
import android.view.View.OnClickListener;
import base.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.layoutId_MainActivity());
        View view = findViewById(R.id.btn1);
        if (null != view) {
        	view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MainActivity.this.redirectToActivity(AppEmptyActivity.class);
				}        		
        	});
        }
    }
}
