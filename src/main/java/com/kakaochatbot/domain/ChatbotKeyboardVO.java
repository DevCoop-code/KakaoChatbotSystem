package com.kakaochatbot.domain;

public class ChatbotKeyboardVO 
{
	private String type;

	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	@Override
	public String toString() 
	{
		return "KakaOKeyboardVO [type=" + type + "]";
	}
}
