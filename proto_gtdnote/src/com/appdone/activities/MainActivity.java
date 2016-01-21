package com.appdone.activities;

import com.appdone.Res;

import base.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.layoutId_MainActivity());
    }
}
