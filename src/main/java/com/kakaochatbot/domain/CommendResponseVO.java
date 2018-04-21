package com.kakaochatbot.domain;

public class CommendResponseVO 
{
	private String responseMessage;
	private String responseImage;
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseImage() {
		return responseImage;
	}
	public void setResponseImage(String responseImage) {
		this.responseImage = responseImage;
	}
	@Override
	public String toString() {
		return "CommendResponseVO [responseMessage=" + responseMessage + ", responseImage=" + responseImage + "]";
	}
}
