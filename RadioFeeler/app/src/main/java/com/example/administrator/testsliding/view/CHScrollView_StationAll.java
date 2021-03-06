package com.example.administrator.testsliding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.example.administrator.testsliding.tab1.Station_AllResult;

public class CHScrollView_StationAll extends HorizontalScrollView {

	Station_AllResult  activity;

	public CHScrollView_StationAll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		activity = (Station_AllResult) context;
	}


	public CHScrollView_StationAll(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (Station_AllResult) context;
	}

	public CHScrollView_StationAll(Context context) {
		super(context);
		activity = (Station_AllResult) context;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//进行触摸赋值
		activity.mTouchView = this;
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//当当前的CHSCrollView被触摸时，滑动其它
		if(activity.mTouchView == this) {
			activity.onScrollChanged(l, t, oldl, oldt);
		}else{
			super.onScrollChanged(l, t, oldl, oldt);
		}
	}
}
