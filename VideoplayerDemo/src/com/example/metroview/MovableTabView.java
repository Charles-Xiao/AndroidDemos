package com.example.metroview;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * @author xiamx
 */

public class MovableTabView extends ImageView {
	private FrameLayout.LayoutParams mLP; 
	public MovableTabView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setLP(FrameLayout.LayoutParams lp)  
    {  
        mLP = lp;  
    }  
      
    /** 
     * ���������ı䲼�ֲ�������˸���� 
     */  
    @Override  
    protected void onAnimationEnd()  
    {  
        setLayoutParams(mLP);  
        super.onAnimationEnd();  
    } 
}
