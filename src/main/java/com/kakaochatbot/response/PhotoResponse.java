package com.kakaochatbot.response;

public class PhotoResponse 
{
	private String url;
	private int width;
	private int height;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "PhotoResponse [url=" + url + ", width=" + width + ", height=" + height + "]";
	}
}
