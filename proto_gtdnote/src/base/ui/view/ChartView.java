package base.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public abstract class ChartView extends android.view.SurfaceView implements android.view.SurfaceHolder.Callback {

	protected Context mContext = null;
	private SurfaceHolder mSurfaceHolder = null;
	
	public ChartView(Context context) {
		super(context);
		mContext = context;
		initSurfaceHolder();
	}

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initSurfaceHolder();
	}

	public ChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initSurfaceHolder();
	}
	
	private void initSurfaceHolder() {
		if (null == mSurfaceHolder) {
			mSurfaceHolder = this.getHolder();//获取holder
			mSurfaceHolder.addCallback(this);
			setZOrderOnTop(true);
			mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);			
		}
	}
	
	public void repaint() {
    	android.graphics.Canvas c = null;
        try {
        	c = mSurfaceHolder.lockCanvas();
        	paint(c);
        }
        finally {
            if (c != null) {
            	mSurfaceHolder.unlockCanvasAndPost(c);
            }
        } 
    } 

	//这里的代码跟继承View时OnDraw中一样
    protected abstract void paint(android.graphics.Canvas ACanvas);
	//protected void paint(android.graphics.Canvas ACanvas) {}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {  
		Canvas canvas = holder.lockCanvas(null);// 获取画布
        canvas.drawColor(Color.WHITE);// 设置画布背景
        holder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置触摸模式
		    case MotionEvent.ACTION_DOWN:
		    	//App.showToast("ACTION_DOWN");
		    	break;
			case MotionEvent.ACTION_POINTER_DOWN:
		    	//App.showToast("ACTION_POINTER_DOWN");
				break;
			case MotionEvent.ACTION_UP:
		    	//App.showToast("ACTION_UP");
		    	break;
			case MotionEvent.ACTION_POINTER_UP:
		    	//App.showToast("ACTION_POINTER_UP");
		    	break;
			case MotionEvent.ACTION_MOVE:
				//App.showToast("ACTION_MOVE");
				break;
			case MotionEvent.ACTION_CANCEL:
				break;
			case MotionEvent.ACTION_OUTSIDE:
				break;
			case MotionEvent.ACTION_SCROLL:
				break;
			case MotionEvent.ACTION_HOVER_ENTER:
				break;
			case MotionEvent.ACTION_HOVER_EXIT:
				break;
			case MotionEvent.ACTION_HOVER_MOVE:
				break;
		}
		return true;
	}
}
