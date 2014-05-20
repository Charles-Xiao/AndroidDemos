package com.example.metroview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;


public class MyScrollView extends HorizontalScrollView {
	private Runnable scrollerTask;
    private int intitPosition;
    private int newCheck = 100;
    private int childWidth = 0;
    public  Boolean canScrollRight=true;

    public interface OnScrollStopListner
    {
        /**
         * scroll have stoped, and is at left edge
         */
        void onScrollToLeftEdge();
        /**
         * scroll have stoped, and is at right edge
         */
        void onScrollToRightEdge();
    }

    private OnScrollStopListner onScrollstopListner;

    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        scrollerTask = new Runnable()
        {
            @Override
            public void run()
            {
                int newPosition = getScrollX();
                if (intitPosition - newPosition == 0)
                {
                    if(onScrollstopListner == null)
                    {
                        return;
                    }
                    Rect outRect = new Rect();
                    getDrawingRect(outRect);
                    if(getScrollX() == 0)
                    {
                        onScrollstopListner.onScrollToLeftEdge();
                    }
                    else if(childWidth + getPaddingLeft() + getPaddingRight() == outRect.right)
                    {
                        onScrollstopListner.onScrollToRightEdge();
                        canScrollRight = false;
                        return;
                    }
                    canScrollRight = true;
                } else
                {
                	intitPosition = getScrollX();
                	Rect outRect = new Rect();
                    getDrawingRect(outRect);
                    if(Math.abs(childWidth + getPaddingLeft() + getPaddingRight()- outRect.right) < 30)
                    {
                    	canScrollRight = false;
                    	return;
                    }
                	canScrollRight = true;
                    postDelayed(scrollerTask, newCheck);
                }
            }
        };
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
    	startScrollerTask();
    	if(canScrollRight)
    		return super.onTouchEvent(ev);
    	return true;
	}
    
    public void setOnScrollStopListner(OnScrollStopListner listner)
    {
        onScrollstopListner = listner;
    }

    public void startScrollerTask()
    {
        intitPosition = getScrollX();
        postDelayed(scrollerTask, newCheck);
        checkTotalWidth();
    }
    private void checkTotalWidth()
    {
        if(childWidth > 0)
        {
            return;
        }
        for(int i = 0; i < getChildCount(); i++)
        {
            childWidth += getChildAt(i).getWidth();
        }
    }
	
}
