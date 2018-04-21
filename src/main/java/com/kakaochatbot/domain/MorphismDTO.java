package com.kakaochatbot.domain;

public class MorphismDTO 
{
	private int type;
	private String keyMaker;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getKeyMaker() {
		return keyMaker;
	}
	public void setKeyMaker(String keyMaker) {
		this.keyMaker = keyMaker;
	}
	@Override
	public String toString() {
		return "MorphismDTO [type=" + type + ", keyMaker=" + keyMaker + "]";
	}
}
