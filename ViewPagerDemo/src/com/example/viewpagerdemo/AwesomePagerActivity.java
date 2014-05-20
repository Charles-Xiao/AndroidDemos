package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

public class AwesomePagerActivity extends Activity {
    
	private BounceBackViewPager awesomePager;

	private Context cxt;
	private AwesomePagerAdapter awesomeAdapter;
	
	private LayoutInflater mInflater;
	private List<View> mListViews;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	
        cxt = this;
        mListViews = new ArrayList<View>();
        mInflater = getLayoutInflater();
        mListViews.add(mInflater.inflate(R.layout.page01, null));
        mListViews.add(mInflater.inflate(R.layout.page02, null));
        mListViews.add(mInflater.inflate(R.layout.page03, null));
        mListViews.add(mInflater.inflate(R.layout.page04, null));
        awesomeAdapter = new AwesomePagerAdapter();
        awesomePager = (BounceBackViewPager) findViewById(R.id.awesomepager);
        awesomePager.setAdapter(awesomeAdapter);
        //awesomePager.setPageMargin(15);
        
        
    }
    
    private class AwesomePagerAdapter extends PagerAdapter{

		
		@Override
		public int getCount() {
			return mListViews.size();
		}
		
		@Override
		public float getPageWidth(int position) 
		{ return(0.6f); } 

	    /**
	     * 从指定的position创建page
	     *
	     * @param container ViewPager容器
	     * @param position The page position to be instantiated.
	     * @return 返回指定position的page，这里不需要是一个view，也可以是其他的视图容器.
	     */
		@Override
		public Object instantiateItem(View collection, int position) {

			
			((ViewPager) collection).addView(mListViews.get(position),0);
			mListViews.get(position).setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
			return mListViews.get(position);
		}

	    /**
	     * <span style="font-family:'Droid Sans';">从指定的position销毁page</span>
	     * 
	     * 
	     *<span style="font-family:'Droid Sans';">参数同上</span>
	     */
		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView(mListViews.get(position));
		}

		
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==(object);
		}

		@Override
		public void finishUpdate(View arg0) {}
		

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {}
    	
    }
    
    
}
