package com.example.metroview;

import com.example.videoplayerdemo.R;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author xiamx
 */

public class TabFrameLayout extends FrameLayout {
	TextView[] tabButton;
	private MovableTabView mTopView;  
    private FrameLayout.LayoutParams mTopViewLP;
    private int mCurindex = 0;
    TabPageChangeListener mTabPageCtrl=null;

	public TabFrameLayout(Context context) {
		this(context,null,0);
		// TODO Auto-generated constructor stub
	}
	
	public TabFrameLayout(Context context,AttributeSet paramAttributeSet) {
		this(context,paramAttributeSet,0);
		// TODO Auto-generated constructor stub
	}
	
	public TabFrameLayout(Context context,AttributeSet paramAttributeSet,int paramInt) {
		super(context,paramAttributeSet,paramInt);
		// TODO Auto-generated constructor stub
	}
	
	public void setPageTabControl(TabPageChangeListener ctrl){
		mTabPageCtrl = ctrl;
	}
	
	public void CreateTabButton(String[] titlelist){
		tabButton = new TextView[titlelist.length];
		int width = getResources().getDimensionPixelSize(R.dimen.tabframe_width);
	 	int height = getResources().getDimensionPixelSize(R.dimen.tabframe_height);
		for (int i = 0; i < titlelist.length; i++) { 
		 	tabButton[i] = new TextView(this.getContext());  
		 	tabButton[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AnimationTab(v);
					if(mTabPageCtrl!=null)
						mTabPageCtrl.SelPageTab(mCurindex);
				}
			});
		 	tabButton[i].setText(titlelist[i]);
		 	tabButton[i].setTextSize(getResources().getDimensionPixelSize(R.dimen.tabtite_fontsize));
		 	tabButton[i].setPadding(0, 0, 5, 0);
		 	tabButton[i].setGravity(Gravity.CENTER);
	        //ColorStateList csl = (ColorStateList)getResources().getColorStateList(R.drawable.tab_txtcolor);
	        //tabButton[i].setTextColor(csl);
		 	tabButton[mCurindex].setTextColor(0xff00ff00);
	        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(width,height);  
	        flp.leftMargin = i * width;  
	        flp.gravity = Gravity.LEFT ;  
	        tabButton[i].setLayoutParams(flp);  
	        this.addView(tabButton[i]);  
		} 
		mTopView = new MovableTabView(this.getContext()); 
	    mTopViewLP = new FrameLayout.LayoutParams(width, height);  
	    //mTopViewLP.gravity = Gravity.LEFT ;  
	    mTopView.setImageResource(R.drawable.launcher_tab_item_focused);  
	    mTopView.setLayoutParams(mTopViewLP);  
	    this.addView(mTopView);
	}
	
	public void AnimationTab(View v){
		final int viewLeft = v.getLeft();  
        final int delta = v.getLeft() - mTopView.getLeft();   
        mTopViewLP.leftMargin = viewLeft;
        mTopView.setLP(mTopViewLP);
        ((TextView)v).setTextColor(0xFF00FF00);
        for(int j=0;j<tabButton.length;j++)
        {
        	if(tabButton[j]==v)
        	{
        		mCurindex = j;
        	}else
        	{
        		tabButton[j].setTextColor(0xFFFFFFFF);
        	}
        }
        TranslateAnimation ani = new TranslateAnimation(0, delta, 0, 0);  
        ani.setDuration(100);  
        mTopView.startAnimation(ani);  
	}
	
	public void SelectAniTab(int index){
		if(index!=mCurindex && index < tabButton.length)
			AnimationTab(tabButton[index]);
	}
	
	public int getTabCount(){
		return (tabButton!=null) ? tabButton.length:0;
	}
	
	public interface TabPageChangeListener{
		void SelPageTab(int index);
	}
	
}
