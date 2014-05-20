package com.example.metroview;

import java.io.File;

public class VideoBeans {
	public String resourceUrl;
	public String videoName;
	public String videoDetail;
	public String Url;
	public VideoBeans(String resUrl,String name,String detail,String url){
		File file = new File(resUrl);
		if(file.exists())
		{
			resourceUrl = resUrl;
		}else
		{
			resourceUrl = "";
		}
		
		videoName = name;
		videoDetail = detail;
		Url = url;
	}
}
