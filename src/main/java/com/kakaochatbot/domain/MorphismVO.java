package com.kakaochatbot.domain;

public class MorphismVO 
{
	private String target;
	private String next;
	private int frequent;
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public int getFrequent() {
		return frequent;
	}
	public void setFrequent(int frequent) {
		this.frequent = frequent;
	}
	@Override
	public String toString() {
		return "MorphismVO [target=" + target + ", next=" + next + ", frequent=" + frequent + "]";
	}
}
