package com.kakaochatbot.domain;

public class InsultWordVO 
{
	private int bno;
	private String insult_word;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getInsult_word() {
		return insult_word;
	}
	public void setInsult_word(String insult_word) {
		this.insult_word = insult_word;
	}
	
	@Override
	public String toString() {
		return "InsultWordVO [bno=" + bno + ", insult_word=" + insult_word + "]";
	}
}
