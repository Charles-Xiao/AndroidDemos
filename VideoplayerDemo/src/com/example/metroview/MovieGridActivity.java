package com.example.metroview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.videoplayerdemo.R;
import com.example.videoplayerdemo.VideoPlayerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MovieGridActivity extends Activity implements TabFrameLayout.TabPageChangeListener {
	private static final String TAG = "MovieGridActivity"; 
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private int PAGESIZE = 8;
	List<CourseBean> courseBeans;
    private String[] tilteTabtext = {
    	"影视分类",
    	"最新推荐",
    	"系统设置"
    };
    
    private int [] videoItemColNum = {
    	4,4,3
    };
    
    TabFrameLayout Tabfl;
    //HorizontalScrollView videtypeTabpage;
    HorizontalScrollView videtypeTabpage;
    //View convertView; 如果使用view类对象来获取HorizontalScrollView控件，对里面的LinearLayout无法添加监听事件
    LinearLayout ltLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moviegrid);
		Tabfl =  (TabFrameLayout) findViewById(R.id.tabutton);
		Tabfl.CreateTabButton(tilteTabtext);
		Tabfl.setPageTabControl(this);
		courseBeans = new ArrayList<CourseBean>();
		loadData();
		initViewPager();
		displaydpi();
		
	}
	
	private void displaydpi(){
		String str = ""; 
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getApplicationContext().getResources().getDisplayMetrics(); 
        int screenWidth = dm.widthPixels; 
        int screenHeight = dm.heightPixels; 
        float density = dm.density; 
        str += "��Ļ�ֱ���Ϊ:" + dm.widthPixels + " * " + dm.heightPixels + "\n"; 
        str += "���Կ��:" + String.valueOf(screenWidth) + "pixels\n"; 
        str += "���Ը߶�:" + String.valueOf(screenHeight) 
                + "pixels\n"; 
        str += "�߼��ܶ�:" + String.valueOf(density) 
                + "\n"; 
        str += "��Ļ�� :" + String.valueOf(dm.widthPixels/density) + "dp\n"; 
        str += "��Ļ��:" + String.valueOf(dm.heightPixels/density) + "dp\n"; 
        Log.i("displaydpi", str); 
	}
	
	private void initViewPager() {
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		List<View> contentGridViews = new ArrayList<View>();

		//test
		videtypeTabpage = (HorizontalScrollView)getLayoutInflater().inflate(R.layout.layout_videotype_page, null);
		
		 ltLayout = (LinearLayout)videtypeTabpage.findViewById(R.id.metroItem1);
		 ltLayout.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.e("test", "点击成功了");
					Toast.makeText(MovieGridActivity.this, "click1", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(MovieGridActivity.this,VideoPlayerActivity.class);
					MovieGridActivity.this.startActivity(i);

				}
			});
		
		
		contentGridViews.add(videtypeTabpage);
		
		for (int i = 0; i < courseBeans.size() / PAGESIZE + 1; i++) {
			//Log.e("tag", courseBeans.size() / PAGESIZE+1+" "+courseBeans.size()+" "+PAGESIZE + " "+i);
			GridView gridView = new GridView(this);
			int start = i * PAGESIZE;
			int end = i * PAGESIZE + PAGESIZE;
			end = end > courseBeans.size() ? courseBeans.size() : end;
			gridView.setAdapter(new CourseAdapter(courseBeans.subList(start,end), this));
			gridView.setNumColumns(videoItemColNum[i]);
			gridView.setPadding(45, 0, 45, 0);
			contentGridViews.add(gridView);// three page has been adaptered
		}
		//advPager.setOffscreenPageLimit(3);courseBeans = new ArrayList<CourseBean>();courseBeans = new ArrayList<CourseBean>();dimens.xmldimens.xmldimens.xml
		advPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
		advPager.setAdapter(new PageAdapter(contentGridViews));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
	}

	/**
	 *加载资源	 */
	private void loadData() {
		
		courseBeans.add(new CourseBean(R.color.nowpublic,"ֱ唐诗",R.drawable.metro_icon1,""));
		courseBeans.add(new CourseBean(R.drawable.metro_bg2,"宋词",R.drawable.metro_icon2,""));
		courseBeans.add(new CourseBean(R.drawable.metro_bg3,"舞蹈",R.drawable.metro_icon3,""));
		courseBeans.add(new CourseBean(R.color.police,"戏剧",R.drawable.metro_icon4,""));
		courseBeans.add(new CourseBean(R.color.idcard,"歌曲",R.drawable.metro_icon5,""));
		courseBeans.add(new CourseBean(R.color.clean,"元曲ʷ",R.drawable.metro_icon6,""));
		courseBeans.add(new CourseBean(R.color.business,"小品",R.drawable.metro_icon7,""));
		courseBeans.add(new CourseBean(R.color.building,"POP",R.drawable.metro_icon8,""));
		
		courseBeans.add(new CourseBean(R.drawable.metro_moive1,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive2,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive3,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive4,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive5,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive6,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive7,"����",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive8,"����",0,""));
		
		courseBeans.add(new CourseBean(R.color.traffic,"ֱ������",R.drawable.metro_icon9,""));
		courseBeans.add(new CourseBean(R.color.chat,"�㲥����",R.drawable.metro_icon10,""));
		courseBeans.add(new CourseBean(R.color.student,"�ʺ�����",R.drawable.metro_icon11,""));
		courseBeans.add(new CourseBean(R.color.lightbulb,"����Ȩ��",R.drawable.metro_icon12,""));
	}

	/**
	 * 页面滑动更改监听
	 * 
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			what.getAndSet(arg0);
			Tabfl.SelectAniTab(arg0);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

	}
	
	@Override
	public void SelPageTab(int index) {
		// TODO Auto-generated method stub
		if(index!=advPager.getCurrentItem())
		{
			advPager.setCurrentItem(index);
		}
			
	}
}
