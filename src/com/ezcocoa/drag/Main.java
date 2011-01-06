package com.ezcocoa.drag;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Main extends Activity implements OnTouchListener {
	private static final String TAG = Main.class.getSimpleName();
	private int DISPLAY_PIXEL_HEIGHT;
	private int DISPLAY_PIXEL_WIDTH;
	private float DISPLAY_DENSITY;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //ÇØ»óµµ Ãëµæ
        // get resolution
        DISPLAY_PIXEL_HEIGHT = getResources().getDisplayMetrics().heightPixels;  //480
        DISPLAY_PIXEL_WIDTH  = getResources().getDisplayMetrics().widthPixels;   //320
        DISPLAY_DENSITY = getResources().getDisplayMetrics().density;
    }
    
	private int _xCur;
	private int _yCur;
	private int _xPre;
	private int _yPre;
	private boolean _isTouched;
	private final int HORIZ_SWIPE_DRAG_MIN = 40;
	private final int VERT_SWIPE_DRAG_MAX = 30;
	private int _direct;
	private static final int RIGHT = 1;
	private static final int LEFT = 0;
	private int fabsf(int a) {
		return a>0?a:-a;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int pc = event.getPointerCount();
		int action = event.getAction() & MotionEvent.ACTION_MASK;

		int xTemp = 0, yTemp = 0;
	  
		_xCur = (int)event.getX(0);
		_yCur = (int)event.getY(0);
	 
		switch(action) {
			case MotionEvent.ACTION_DOWN :
				Log.d(TAG, "onTouchEvent-ACTION_DOWN");
				_isTouched = true;
				_xPre = _xCur;
				_yPre = _yCur;
				break;
				
			case MotionEvent.ACTION_MOVE :
				Log.d(TAG, "onTouchEvent-ACTION_MOVE");
				_isTouched = false;
//				if ((fabsf(_xPre-_xCur) >= HORIZ_SWIPE_DRAG_MIN ) && 
//						(fabsf(_xPre-_xCur) <= VERT_SWIPE_DRAG_MAX)) { 
						if (_xPre < _xCur) {
							_direct = RIGHT;
						} else {
							_direct = LEFT;
						}
//				}
				break;
				
			case MotionEvent.ACTION_UP :
				Log.d(TAG, "onTouchEvent-ACTION_UP");
				if (_direct == RIGHT) {
					Log.d(TAG, "onTouchEvent-RIGHT");
				} else {
					Log.d(TAG, "onTouchEvent-LEFT");
				}
				break;
		}
		return true;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}