package com.ui.activities;

import module.res.ComRes;
import base.ui.BaseActivity;

public class AppEmptyActivity extends BaseActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ComRes.layoutId_AppSplashActivity());
    }
}
