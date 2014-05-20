package com.example.metroview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyChildViewPager extends ViewPager {
	boolean canScroll = false; 

	public MyChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyChildViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void SetScrollable(Boolean bcanScroll){
		canScroll = bcanScroll;
	}
	@Override
	public void scrollTo(int x, int y) {
		// TODO Auto-generated method stub
		if(canScroll)
			super.scrollTo(x, y);
	}
}
