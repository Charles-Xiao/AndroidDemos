package com.example.metroview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoplayerdemo.R;
import com.example.videoplayerdemo.VideoPlayerActivity;


public class CourseAdapter extends BaseAdapter {
	private List<CourseBean> courseBeans;
	private Context context;

	public CourseAdapter(List<CourseBean> courseBeans, Context context) {
		super();
		this.courseBeans = courseBeans;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return courseBeans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return courseBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final CourseBean courseBean = courseBeans.get(position);
		LayoutInflater mInflater = (LayoutInflater) ((Activity) context)
				.getLayoutInflater();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.metro_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.image);
			holder.textView = (TextView) convertView.findViewById(R.id.text);
			holder.rl = (LinearLayout) convertView.findViewById(R.id.rl);
			Log.i("getView", "+++++:list" + position + ",width" + holder.rl.getLayoutParams().width);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(courseBean.getImageView()!=0)
			{
			holder.imageView.setBackgroundResource(courseBean.getImageView());
			holder.textView.setText(courseBean.getFunctionName());
			}
		holder.rl.setBackgroundResource(courseBean.getBackgroundColor());
		// Display display = ((Activity)
		// context).getWindowManager().getDefaultDisplay();
		// int height = display.getHeight();
		// int width = display.getWidth();
		// LayoutParams lp = (LayoutParams)convertView.getLayoutParams();
		// lp.width = width/4-10;
		// lp.height = height/4-20;
		// convertView.setLayoutParams(lp);
		convertView.setPadding(2, 2, 2, 2);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context,VideoPlayerActivity.class);
				context.startActivity(i);
			}

		});
		return convertView;
	}

	public class ViewHolder {
		public ImageView imageView;
		public TextView textView;
		public LinearLayout rl;
	}
}
