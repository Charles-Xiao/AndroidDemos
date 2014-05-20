package com.example.metroview;


public class CourseBean {
	private int backgroundColor;
	private String functionName;
	private int  imageView;
	private String url;
	

	public CourseBean(int backgroundColor, String functionName,int imageView,String url) {
		super();
		this.backgroundColor = backgroundColor;
		this.functionName = functionName;
		this.imageView = imageView;
		this.url = "    " + url;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getImageView() {
		return imageView;
	}

	public void setImageView(int imageView) {
		this.imageView = imageView;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
