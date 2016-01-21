package base.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import base.utils.log;

//import android.content.pm.ActivityInfo;
//import android.view.Window;

public abstract class BaseActivity  extends android.app.Activity {

	private static String LOGTAG = BaseActivity.class.getSimpleName();
	private boolean mIsCreated;
	
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
    	setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	//BaseApp.core.getActivitiesManager().addActivity(this);
    	mIsCreated = true;
    }

	@Override
	protected void onDestroy() {
		log.d(LOGTAG + " " + BaseActivity.this.getClass().getSimpleName() + " onDestroy");
		try {
		    if (null != mActivityBroadcastReceiver) {
		    	unregisterReceiver(mActivityBroadcastReceiver);
		    	mActivityBroadcastReceiver = null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null !=mActivityMessageHandler) {
			mActivityMessageHandler = null;
		}
		if (null != mProgressDialog) {
			mProgressDialog.cancel();
			mProgressDialog = null;
		}
		super.onDestroy();
		//BaseApp.core.getActivitiesManager().finishActivity(this);
	}    

	protected Handler mActivityMessageHandler = null;	
	protected android.content.BroadcastReceiver mActivityBroadcastReceiver = null;
	//protected String mActivityType = null;
	protected ProgressDialog mProgressDialog = null;	 


	@Override
	protected void onStart() {
		super.onStart();
		//BaseApp.core.getAnalysisManager().addActivityOnStartEvent(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		//BaseApp.core.getAnalysisManager().addActivityOnStopEvent(this);
	}
	
	@Override
	public void finish() {
		super.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mIsCreated)
		{
			mIsCreated = false;
			//initParams();
			//initTitleParam();
		}
		//BaseApp.core.getAnalysisManager().addActivityOnResumeEvent(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		//BaseApp.core.getAnalysisManager().addActivityOnPauseEvent(this);
	}	
	

	public void redirectToActivity (Class destActivityClass) {
		Intent intent = new Intent();
		intent.setClass(this, destActivityClass);
		this.startActivity(intent);
	}	

	public void redirectToActivity (Class destActivityClass, String param) {
		Intent intent = new Intent();
		intent.setClass(this, destActivityClass);
		//intent.putExtra(BaseConsts.EXTRA_ACTIVITY_PARAM, param);
		this.startActivity(intent);
	}
	
	public void redirectToActivity (Class destActivityClass, String title, String param) {
		android.content.Intent intent = new android.content.Intent();
		intent.setClass(this, destActivityClass);
		//intent.putExtra(BaseConsts.EXTRA_ACTIVITY_TITLE, title);
		//intent.putExtra(BaseConsts.EXTRA_ACTIVITY_PARAM, param);
		this.startActivity(intent);
	}	

	public void redirectToActivity (Class destActivityClass, int requestCode) {
		android.content.Intent intent = new android.content.Intent();
		intent.setClass(this, destActivityClass);
		this.startActivityForResult(intent, requestCode);
	}	

	protected String idStr(int id) {
		return getResources().getString(id);
	}

	protected int idColor(int id) {
		return getResources().getColor(id);
	}

	protected float idDimen(int id) {
		return getResources().getDimension(id);
	}	
	
	public void callCloseActivity(){
		 if (null != mActivityMessageHandler) {
			 //mActivityMessageHandler.sendEmptyMessage(BaseConsts.MSG_ACTIVITY_CLOSE);
		 }
	}

	public Handler getBaseMsgHandler() {
		return mActivityMessageHandler;
	}

	public void sendMessage(int msg, Object obj){
		createMessageLoopHandler();
		if (null != mActivityMessageHandler) {
			Message msgobj = mActivityMessageHandler.obtainMessage();
			msgobj.obj = obj;  
			msgobj.what = msg;  
			msgobj.sendToTarget();
		}
	}
	
	protected void createMessageLoopHandler(){
		if (null != mActivityMessageHandler)
			return;
		Looper loop = Looper.getMainLooper();
		mActivityMessageHandler = new Handler(loop){
		    @Override
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        handleActivityMessage(msg);
		    }
		};
	}

	protected boolean handleActivityMessage(Message msg){
		/*//
        switch (msg.what) {  
            case BaseConsts.MSG_TOAST:        	  
      	        Toast.makeText(this, (String)msg.obj, Toast.LENGTH_LONG).show();
      	        return true;
             case BaseConsts.MSG_ACTIVITY_CLOSE:
        	     finish();
       	        return true;
        }
		//*/
		return false;
	}
}
