package com.service;

import com.modnet.tcpserver;

import android.app.Service;

public class server extends base {
	
	private static tcpserver mTcpServer = null;
	public void onCreate()  
	{  
	    super.onCreate();  
		System.out.println("Service is created");
		mTcpServer = new tcpserver();
	    new Thread()  
	    {  
	        public void run()  
	        {  
	        	mTcpServer.open();  
	        };  
	    }.start();  
	}
	
	public void onDestory()  
	{  
		System.out.println("Service is destroyed");
		if (null != mTcpServer) {
		    mTcpServer.close();			
		} 
	    super.onDestroy();  
	}  
	 
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	
}
