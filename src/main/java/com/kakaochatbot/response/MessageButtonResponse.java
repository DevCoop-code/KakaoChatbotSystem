package com.kakaochatbot.response;

public class MessageButtonResponse 
{
	private String label;
	private String url;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "MessageButtonResponse [label=" + label + ", url=" + url + "]";
	}
}
